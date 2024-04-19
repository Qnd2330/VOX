package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDTO;
import VOX_Giat_La.DTO.SalaryDetailDTO;
import VOX_Giat_La.Models.Salary;
import VOX_Giat_La.Service.Salary.ISalaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final ISalaryService salaryService;
    @GetMapping("/list") // http://localhost:2330/VOX/salary/list
    public ResponseEntity<?> getAllSalary() {
        List<Salary> salary = salaryService.getListSalary();
        return ResponseEntity.ok(salary);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSalaryByID(@PathVariable int id) throws Exception {
        Salary salary = salaryService.getSalaryByID(id);
        return ResponseEntity.ok(salary);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/salary/insert
    public ResponseEntity<?> insertSalary(@Valid @RequestBody SalaryDTO salaryDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Salary salary = salaryService.createSalary(salaryDTO);
            return ResponseEntity.ok(salary);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/salary/update
    public ResponseEntity<String> updateSalary(@PathVariable int id, SalaryDTO salaryDTO) throws Exception {
        Salary salary = salaryService.updateSalary(id,salaryDTO);
        return ResponseEntity.ok("Cập nhật Salary");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/salary/delete
    public ResponseEntity<String> deleteSalary(@PathVariable int id) {
        salaryService.deleteSalary(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Salary "+ id);
    }
}
