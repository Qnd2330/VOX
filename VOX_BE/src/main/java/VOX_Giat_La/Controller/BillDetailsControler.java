package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.DTO.BillDetailsDTO;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Repositories.BillDetailsRepos;
import VOX_Giat_La.Respones.BillDetailListRespone;
import VOX_Giat_La.Respones.BillDetailRespone;
import VOX_Giat_La.Respones.BillListRespone;
import VOX_Giat_La.Respones.BillRespones;
import VOX_Giat_La.Service.Bill.IBillService;
import VOX_Giat_La.Service.BillDetails.IBillDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/bill_details")
@RequiredArgsConstructor
public class BillDetailsControler {
    private final IBillDetailsService billDetailsService;
    private final IBillService billService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getAllBillDetails(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("billDetailID").descending());
            Page<BillDetailRespone> billsPage = billDetailsService.getListBillDetails(pageRequest);
            int totalPages = billsPage.getTotalPages();
            List<BillDetailRespone> billDetail = billsPage.getContent();
            return ResponseEntity.ok(BillDetailListRespone.builder()
                    .billDetail(billDetail)
                    .totalPages(totalPages)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")  // http://localhost:2330/VOX/bill_details/{id}
    public ResponseEntity<?> findBillDetailsByID(@Valid @PathVariable int id) {
        try{
            BillDetails existingBillDetail = billDetailsService.getBillDetailsByID(id);
            return ResponseEntity.ok(BillDetailRespone.fromBillDetail(existingBillDetail));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Không tìm thấy bill detail với id trên");
        }
    }
    @GetMapping("Bill/{billID}")  // http://localhost:2330/VOX/bill_details/Bill/{billID}
    public ResponseEntity<?> findBillDetailsByBillID(@Valid @PathVariable int billID) {
        try{
            List<BillDetails> existingBillDetail = billDetailsService.getBillDetailsByBill(billID);
            return ResponseEntity.ok(existingBillDetail);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Không tìm thấy bill với id trên");
        }

    }



    @PostMapping("/insert/{billID}") //  http://localhost:2330/VOX/bill_details/insert
    public ResponseEntity<?> insertBillDetails(@PathVariable int billID, @Valid @RequestBody BillDetailsDTO billDetailsDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            BillDetails billDetails = billDetailsService.createBillDetails(billID,billDetailsDTO);
            return ResponseEntity.ok("Thêm mới BillDetails" + billDetails);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/bill_details/update
    public ResponseEntity<?> updateBillDetails(@Valid @PathVariable int id , @RequestBody BillDetailsDTO newBillDetailsDTO) {
        try{
            return ResponseEntity.ok(billDetailsService.updateBillDetails(id,newBillDetailsDTO));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/bill_details/delete
    public ResponseEntity<String> deleteBill(@Valid@PathVariable int id) {
        try{
            billDetailsService.deleteBillDetails(id);
            return ResponseEntity.ok("Đã xóa thành công !");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Không tìm thấy bill với id trên");
        }
    }
}
