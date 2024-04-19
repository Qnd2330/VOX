package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDetailDTO;
import VOX_Giat_La.Models.SalaryDetail;
import VOX_Giat_La.Service.Salary.ISalaryDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/salarydetail")
@RequiredArgsConstructor
public class SalaryDetailController {
    private final ISalaryDetailService salaryDetailService;
    @GetMapping("/list") // http://localhost:2330/VOX/salarydetail/list
    public ResponseEntity<?> getAllSalaryDetail() {
        List<SalaryDetail> salaryDetails = salaryDetailService.getListSalaryDetail();
        return ResponseEntity.ok(salaryDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSalaryDetailByID(@PathVariable int id) throws Exception {
        SalaryDetail salaryDetail = salaryDetailService.getSalaryDetailByID(id);
        return ResponseEntity.ok(salaryDetail);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/salarydetail/insert
    public ResponseEntity<?> insertSalaryDetail(@Valid @RequestBody SalaryDetailDTO salaryDetailDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            SalaryDetail salaryDetail = salaryDetailService.createSalaryDetail(salaryDetailDTO);
            return ResponseEntity.ok(salaryDetail);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/salarydetail/update
    public ResponseEntity<String> updateSalaryDetail(@PathVariable int id,@Valid @RequestBody SalaryDetailDTO salaryDetailDTO) throws Exception {
        SalaryDetail salaryDetail = salaryDetailService.updateSalaryDetail(id,salaryDetailDTO);
        return ResponseEntity.ok("Cập nhật SalaryDetail");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/salarydetail/delete
    public ResponseEntity<String> deleteSalaryDetail(@PathVariable int id) {
        salaryDetailService.deleteSalaryDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công SalaryDetail "+ id);
    }
}
