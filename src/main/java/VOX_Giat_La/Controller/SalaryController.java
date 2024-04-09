package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    @GetMapping("/list") // http://localhost:2330/salary/list
    public ResponseEntity<String> getAllSalary() {
        return ResponseEntity.ok(String.format("List Salary"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findSalaryByID(@PathVariable int id) {
        return ResponseEntity.ok("Salary "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/salary/insert
    public ResponseEntity<?> insertSalary(@Valid @RequestBody SalaryDTO salaryDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới Salary" + salaryDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/salary/update
    public ResponseEntity<String> updateSalary(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật Salary");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/salary/delete
    public ResponseEntity<String> deleteSalary(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Salary "+ id);
    }
}
