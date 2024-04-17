package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.Washing_MethodDTO;
import VOX_Giat_La.Models.Washing_Method;
import VOX_Giat_La.Service.Washing_Method.IWashing_MethodService;
import VOX_Giat_La.Service.Washing_Method.Washing_MethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/washing_method")
@RequiredArgsConstructor
public class Washing_MethodController {
    private final IWashing_MethodService washingMethodService;
    @GetMapping("/list") // http://localhost:2330/VOX/washing_method/list
    public ResponseEntity<List<Washing_Method>> getAllWashingMethod() {
        List<Washing_Method> washingMethods = washingMethodService.getListWashing_Method();
        return ResponseEntity.ok(washingMethods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findWashingMethodByID(@PathVariable int id) {
        Washing_Method washingMethod = washingMethodService.getWashing_MethodbyID(id);
        return ResponseEntity.ok("Washing Method "+ washingMethod);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/washing_method/insert
    public ResponseEntity<?> insertWashingMethod(@Valid @RequestBody Washing_MethodDTO washingMethodDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            washingMethodService.createWashing_Method(washingMethodDTO);
            return ResponseEntity.ok("Thêm mới cách giặt" + washingMethodDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/washing_method/update
    public ResponseEntity<?> updateWashingMethod(@PathVariable int id,@Valid @RequestBody Washing_MethodDTO washingMethodDTO ) {
        washingMethodService.updateWashing_Method(id,washingMethodDTO);
        return ResponseEntity.ok("Cập nhật Washing Method"+ washingMethodDTO);
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/washing_method/delete
    public ResponseEntity<?> deleteWashingMethod(@PathVariable int id) {
        washingMethodService.deleteWashing_Method(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Washing Method "+ id);
    }
}
