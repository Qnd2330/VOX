package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/salarydetail")
public class SalaryDetailController {
    @GetMapping("/list") // http://localhost:2330/salarydetail/list
    public ResponseEntity<String> getAllSalaryDetail() {
        return ResponseEntity.ok(String.format("List SalaryDetail"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findSalaryDetailByID(@PathVariable int id) {
        return ResponseEntity.ok("SalaryDetail "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/salarydetail/insert
    public ResponseEntity<?> insertSalaryDetail(@Valid @RequestBody SalaryDetailDTO salaryDetailDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới SalaryDetail" + salaryDetailDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/salarydetail/update
    public ResponseEntity<String> updateSalaryDetail(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật SalaryDetail");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/salarydetail/delete
    public ResponseEntity<String> deleteSalaryDetail(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công SalaryDetail "+ id);
    }
}
