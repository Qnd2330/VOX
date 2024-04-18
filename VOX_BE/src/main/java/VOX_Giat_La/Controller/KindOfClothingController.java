package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.KindOfClothingDTO;
import VOX_Giat_La.DTO.Washing_MethodDTO;
import VOX_Giat_La.Models.KindOfClothing;
import VOX_Giat_La.Models.Washing_Method;
import VOX_Giat_La.Service.KindOfClothing.IKindOfClothingService;
import VOX_Giat_La.Service.Washing_Method.IWashing_MethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/kindofclothing")
@RequiredArgsConstructor
public class KindOfClothingController {
    private final IKindOfClothingService kindOfClothingService;
    @GetMapping("/list") // http://localhost:2330/VOX/kindofclothing/list
    public ResponseEntity<List<KindOfClothing>> getAllKindOfClothing() {
        List<KindOfClothing> kindOfClothings = kindOfClothingService.getListKindOfClothing();
        return ResponseEntity.ok(kindOfClothings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findKindOfClothingByID(@PathVariable int id) {
        KindOfClothing kindOfClothing = kindOfClothingService.getKindOfClothingbyID(id);
        return ResponseEntity.ok("Kind Of Clothing "+ kindOfClothing);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/kindofclothing/insert
    public ResponseEntity<?> insertKindOfClothing(@Valid @RequestBody KindOfClothingDTO kindOfClothingDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            kindOfClothingService.createKindOfClothing(kindOfClothingDTO);
            return ResponseEntity.ok("Thêm mới Kind Of Clothing" + kindOfClothingDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/kindofclothing/update
    public ResponseEntity<String> updateKindOfClothing(@PathVariable int id,@Valid @RequestBody KindOfClothingDTO kindOfClothingDTO) {
        kindOfClothingService.updateKindOfClothing(id,kindOfClothingDTO);
        return ResponseEntity.ok("Cập nhật Kind Of Clothing");
    }

    @DeleteMapping("/delete/{id}") //    h
    // ttp://localhost:2330/VOX/kindofclothing/delete
    public ResponseEntity<String> deleteKindOfClothing(@PathVariable int id) {
        kindOfClothingService.deleteKindOfClothing(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Kind Of Clothing "+ id);
    }
}
