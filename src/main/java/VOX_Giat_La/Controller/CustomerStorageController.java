package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.CustomerStorageDTO;
import VOX_Giat_La.DTO.KindOfClothingDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerstorage")
public class CustomerStorageController {
    @GetMapping("/list") // http://localhost:2330/customerstorage/list
    public ResponseEntity<String> getAllCustomerStorage() {
        return ResponseEntity.ok(String.format("List CustomerStorage"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findCustomerStorageByID(@PathVariable int id) {
        return ResponseEntity.ok("CustomerStorage "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/customerstorage/insert
    public ResponseEntity<?> insertCustomerStorage(@Valid @RequestBody CustomerStorageDTO customerStorageDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới CustomerStorage" + customerStorageDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/customerstorage/update
    public ResponseEntity<String> updateCustomerStorage(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật CustomerStorage");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/customerstorage/delete
    public ResponseEntity<String> deleteCustomerStorage(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công CustomerStorage "+ id);
    }
}
