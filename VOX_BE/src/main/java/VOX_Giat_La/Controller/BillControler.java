package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Respones.BillListRespone;
import VOX_Giat_La.Respones.BillRespones;
import VOX_Giat_La.Service.Bill.BillService;
import VOX_Giat_La.Service.Bill.IBillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/bill")
@RequiredArgsConstructor
public class BillControler {
    private final IBillService billService;
    @GetMapping("/list") // bill/list?page=1&limit=10
    public ResponseEntity<BillListRespone> getAllBill(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        PageRequest pageRequest = PageRequest.of(page,limit, Sort.by("billCreateDate").descending());
        Page<BillRespones> billsPage = billService.getListBill(pageRequest);
        int totalPages = billsPage.getTotalPages();
        List<BillRespones> bills = billsPage.getContent();
        return ResponseEntity.ok(BillListRespone.builder()
                .bills(bills)
                .totalPages(totalPages)
                .build());
    }

    @GetMapping("/{id}") // http://localhost:2330/VOX/bill/{id}
    public ResponseEntity<?> findBillByID(@PathVariable int id) {
        try{
            Bill existingBill = billService.getBillByID(id);
            return ResponseEntity.ok(BillRespones.fromBill(existingBill));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Không tìm thấy bill với id trên");
        }
    }

    @GetMapping("/user/{id}") //  http://localhost:2330/VOX/bill/user/{id}
    public ResponseEntity<?> getBillByUserID (@Valid @PathVariable("id")int userID){
        try{
            Bill existingBill = billService.getBillByUserId(userID);
            return ResponseEntity.ok(BillRespones.fromBill(existingBill));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/bill/insert
    public ResponseEntity<?> createBill(@Valid @RequestBody  BillDTO billDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Bill bill = billService.createBill(billDTO);
            return ResponseEntity.ok("Thêm mới bill" + bill);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages( @PathVariable("id") int id, @ModelAttribute BillDTO billDTO ) throws Exception {
        Bill existingBill = billService.getBillByID(id);
        MultipartFile file = billDTO.getImage();
        if(file != null){
            if(file.getSize() > 10 * 1024 * 1024){
                throw  new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE,"File ảnh của bạn quá lớn, tối đa là 10MB");
            }
            String contectType = file.getContentType();
            if(contectType == null || !contectType.startsWith("image/")){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File này phải là ảnh");
            }
            String filename = storeFile(file);
            existingBill.setImage(filename);
            billService.updateImge(id, billDTO);
        }
        return ResponseEntity.ok("Đã thêm đc ảnh" + existingBill.getImage());
    }

    private String storeFile(MultipartFile file) throws IOException{
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName= UUID.randomUUID().toString() + "_" +fileName;
        Path uploadDir = Paths.get("uploads");
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return  uniqueFileName;
    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/bill/update
    public ResponseEntity<?> updateBill(@Valid @PathVariable int id, @Valid @RequestBody BillDTO billDTO) {
        try{
            return ResponseEntity.ok(billService.updateBill(id,billDTO));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/bill/delete
    public ResponseEntity<?> deleteBill(@PathVariable int id) {
        try{
             billService.deleteBill(id);
            return ResponseEntity.ok("Đã xóa thành công !");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Không tìm thấy bill với id trên");
        }
    }

}
