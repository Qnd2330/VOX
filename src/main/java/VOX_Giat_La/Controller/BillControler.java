package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.BillDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

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

    @PostMapping("/insert") //  http://localhost:2330/bill/insert
    public ResponseEntity<?> insertBill(@Valid @RequestBody BillDTO billDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới bill" + billDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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
