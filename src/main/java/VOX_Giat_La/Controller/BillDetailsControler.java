package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.DTO.BillDetailsDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/bill_details")
public class BillDetailsControler {

    @GetMapping("/list") // http://localhost:2330/VOX/bill_details/list?page=1&limit=10
    public ResponseEntity<String> getAllBillDetails(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok(String.format("List BillDetails,page = %d,limit=%d", page, limit));
    }

    @GetMapping("/{id}")  // http://localhost:2330/VOX/bill_details/{id}
    public ResponseEntity<String> findBillDetailsByID(@Valid @PathVariable int id) {
        return ResponseEntity.ok("BillDetails "+ id);
    }
    @GetMapping("Bill/{billID}")  // http://localhost:2330/VOX/bill_details/Bill/{billID}
    public ResponseEntity<String> findBillDetailsByBillID(@Valid @PathVariable int billID) {
        return ResponseEntity.ok("BillDetails "+ billID);
    }



    @PostMapping("/insert") //  http://localhost:2330/VOX/bill_details/insert
    public ResponseEntity<?> insertBillDetails(@Valid @RequestBody BillDetailsDTO billDetailsDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới BillDetails" + billDetailsDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/bill_details/update
    public ResponseEntity<String> updateBillDetails(@Valid @PathVariable int id , @RequestBody BillDetailsDTO newBillDetailsDTO) {
        return ResponseEntity.ok("Cập nhật BillDetails id= " + id +"New BillDetail "+ newBillDetailsDTO);
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/bill_details/delete
    public ResponseEntity<String> deleteBill(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công BillDetails "+ id);
    }
}
