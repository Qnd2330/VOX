package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.BillDTO;
import jakarta.validation.Valid;
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
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bill")
public class BillControler {
    @GetMapping("/list") // http://localhost:2330/bill/list?page=1&limit=10
    public ResponseEntity<String> getAllBill(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok(String.format("List bill,page = %d,limit=%d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findBillByID(@PathVariable int id) {
        return ResponseEntity.ok("Bill "+ id);
    }

    @PostMapping(value = "/insert",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //  http://localhost:2330/bill/insert
    public ResponseEntity<?> insertBill(@Valid @ModelAttribute BillDTO billDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
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

            }

            return ResponseEntity.ok("Thêm mới bill" + billDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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

    @PutMapping("/update/{id}") //   http://localhost:2330/bill/update
    public ResponseEntity<String> updateBill(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật bill");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/bill/delete
    public ResponseEntity<String> deleteBill(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công bill "+ id);
    }

}
