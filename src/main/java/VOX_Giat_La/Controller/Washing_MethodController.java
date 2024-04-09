package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.Washing_MethodDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/washing_method")
public class Washing_MethodController {
    @GetMapping("/list") // http://localhost:2330/washingmethod/list
    public ResponseEntity<String> getAllWashingMethod() {
        return ResponseEntity.ok(String.format("List Washing Method"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findWashingMethodByID(@PathVariable int id) {
        return ResponseEntity.ok("Washing Method "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/washingmethod/insert
    public ResponseEntity<?> insertWashingMethod(@Valid @RequestBody Washing_MethodDTO washingMethodDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới cách giặt" + washingMethodDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/washingmethod/update
    public ResponseEntity<String> updateWashingMethod(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật Washing Method");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/washingmethod/delete
    public ResponseEntity<String> deleteWashingMethod(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Washing Method "+ id);
    }
}
