package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.ClothingWashingCompatibleDTO;
import VOX_Giat_La.Models.ClothingWashingCompatible;
import VOX_Giat_La.Service.ClothingWashingCompatible.IClothingWashingCompatibleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/clothingwashingcompatible")
@RequiredArgsConstructor
public class ClothingWashingCompatibleController {
    private final IClothingWashingCompatibleService clothingWashingCompatibleService;

    @GetMapping("/list") // http://localhost:2330/VOX/clothingwashingcompatible/list
    public ResponseEntity<List<ClothingWashingCompatible>> getAllClothingWashingCompatible() {
        List<ClothingWashingCompatible> clothingWashingCompatibles = clothingWashingCompatibleService.getListClothingWashingCompatible();
        return ResponseEntity.ok(clothingWashingCompatibles);
    }

    @GetMapping("/{id}") // http://localhost:2330/VOX/clothingwashingcompatible/{id}
    public ResponseEntity<String> findClothingWashingCompatibleByID(@PathVariable int id) {
        ClothingWashingCompatible clothingWashingCompatible = clothingWashingCompatibleService.getClothingWashingCompatiblebyID(id);
        return ResponseEntity.ok("Clothing Washing Compatible " + clothingWashingCompatible);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/clothingwashingcompatible/insert
    public ResponseEntity<?> insertClothingWashingCompatible(@Valid @RequestBody ClothingWashingCompatibleDTO clothingWashingCompatibleDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            clothingWashingCompatibleService.createClothingWashingCompatible(clothingWashingCompatibleDTO);
            return ResponseEntity.ok("Thêm mới Clothing Washing Compatible" + clothingWashingCompatibleDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/clothingwashingcompatible/update
    public ResponseEntity<String> updateClothingWashingCompatible(@PathVariable int id, @Valid @RequestBody ClothingWashingCompatibleDTO clothingWashingCompatibleDTO) throws Exception{
        clothingWashingCompatibleService.updateClothingWashingCompatible(id, clothingWashingCompatibleDTO);
        return ResponseEntity.ok("Cập nhật Clothing Washing Compatible");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClothingWashingCompatible(@PathVariable int id) {
        clothingWashingCompatibleService.deleteClothingWashingCompatible(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Clothing Washing Compatible " + id);
    }
}
