package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDTO;
import VOX_Giat_La.DTO.Work_ScheduleDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/workschedule")
public class Work_ScheduleController {
    @GetMapping("/list") // http://localhost:2330/VOX/workschedule/list
    public ResponseEntity<String> getAllWork_Schedule() {
        return ResponseEntity.ok(String.format("List Work_Schedule"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findWork_ScheduleByID(@PathVariable int id) {
        return ResponseEntity.ok("Work_Schedule "+ id);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/workschedule/insert
    public ResponseEntity<?> insertWork_Schedule(@Valid @RequestBody Work_ScheduleDTO workScheduleDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Thêm mới Work_Schedule" + workScheduleDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/workschedule/update
    public ResponseEntity<String> updateWork_Schedule(@PathVariable int id) {
        return ResponseEntity.ok("Cập nhật Work_Schedule");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/workschedule/delete
    public ResponseEntity<String> deleteWork_Schedule(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Work_Schedule "+ id);
    }
}
