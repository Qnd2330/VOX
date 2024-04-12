package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDetailDTO;
import VOX_Giat_La.DTO.StoreStorageDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/storestorage")
public class StoreStorageController {
    @GetMapping("/list") // http://localhost:2330/storestorage/list
    public ResponseEntity<String> getAllStoreStorage() {
        return ResponseEntity.ok(String.format("List StoreStorage"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findStoreStorageByID(@PathVariable int id) {
        return ResponseEntity.ok("StoreStorage "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/storestorage/insert
    public ResponseEntity<?> insertStoreStorage(@Valid @RequestBody StoreStorageDTO storeStorageDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới StoreStorage" + storeStorageDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/storestorage/update
    public ResponseEntity<String> updateStoreStorage(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật StoreStorage");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/storestorage/delete
    public ResponseEntity<String> deleteStoreStorage(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công StoreStorage "+ id);
    }
}
