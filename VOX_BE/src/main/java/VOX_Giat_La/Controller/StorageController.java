package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.StorageDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/storage")
public class StorageController {
    @GetMapping("/list") // http://localhost:2330/VOX/storage/list
    public ResponseEntity<String> getAllStorage() {
        return ResponseEntity.ok(String.format("List storage"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findStoreStorageByID(@PathVariable int id) {
        return ResponseEntity.ok("Storage "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/storage/insert
    public ResponseEntity<?> insertStorage(@Valid @RequestBody StorageDTO StorageDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới storage" + StorageDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/storage/update
    public ResponseEntity<String> updateStorage(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật storage");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/storage/delete
    public ResponseEntity<String> deleteStorage(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công storage "+ id);
    }
}
