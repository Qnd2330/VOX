package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.ClothingWashingCompatibleDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clothingwashingcompatible")
public class ClothingWashingCompatibleController {
    @GetMapping("/list") // http://localhost:2330/clothingwashingcompatible/list
    public ResponseEntity<String> getAllClothingWashingCompatible() {
        return ResponseEntity.ok(String.format("List ClothingWashingCompatible"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findClothingWashingCompatibleByID(@PathVariable int id) {
        return ResponseEntity.ok("ClothingWashingCompatible"+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/clothingwashingcompatible/insert
    public ResponseEntity<?> insertClothingWashingCompatible(@Valid @RequestBody ClothingWashingCompatibleDTO clothingWashingCompatibleDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới ClothingWashingCompatible" + clothingWashingCompatibleDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/clothingwashingcompatible/update
    public ResponseEntity<String> updateClothingWashingCompatible(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật ClothingWashingCompatible");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/clothingwashingcompatible/delete
    public ResponseEntity<String> deleteClothingWashingCompatible(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công ClothingWashingCompatible "+ id);
    }
}
