package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.KindOfClothingDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kindofclothing")
public class KindOfClothingController {
    @GetMapping("/list") // http://localhost:2330/kindofclothing/list
    public ResponseEntity<String> getAllKindOfClothing() {
        return ResponseEntity.ok(String.format("List Kind Of Clothing"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findKindOfClothingByID(@PathVariable int id) {
        return ResponseEntity.ok("Kind Of Clothing "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/kindofclothing/insert
    public ResponseEntity<?> insertKindOfClothing(@Valid @RequestBody KindOfClothingDTO kindOfClothingDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới Kind Of Clothing" + kindOfClothingDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/kindofclothing/update
    public ResponseEntity<String> updateKindOfClothing(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật Kind Of Clothing");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/kindofclothing/delete
    public ResponseEntity<String> deleteKindOfClothing(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Kind Of Clothing "+ id);
    }
}
