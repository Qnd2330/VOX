package VOX_Giat_La.Controller;

import VOX_Giat_La.DTO.SalaryDTO;
import VOX_Giat_La.DTO.Work_ScheduleDTO;
import VOX_Giat_La.Models.Work_Schedule;
import VOX_Giat_La.Service.Work_Schedule.IWork_ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/workschedule")
@RequiredArgsConstructor
public class Work_ScheduleController {
    private final IWork_ScheduleService workScheduleService;
    @GetMapping("/list") // http://localhost:2330/VOX/workschedule/list
    public ResponseEntity<?> getAllWork_Schedule() {
        List<Work_Schedule> workSchedules = workScheduleService.getListWork_Schedule();
        return ResponseEntity.ok(workSchedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findWork_ScheduleByID(@PathVariable int id) throws Exception {
        Work_Schedule workSchedule = workScheduleService.getWork_ScheduleByID(id);
        return ResponseEntity.ok(workSchedule);
    }

    @PostMapping("/insert") //  http://localhost:2330/VOX/workschedule/insert
    public ResponseEntity<?> insertWork_Schedule(@Valid @RequestBody Work_ScheduleDTO workScheduleDTO, BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Work_Schedule workSchedule = workScheduleService.createWork_Schedule(workScheduleDTO);
            return ResponseEntity.ok(workSchedule);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/update/{id}") //   http://localhost:2330/VOX/workschedule/update
    public ResponseEntity<?> updateWork_Schedule(@PathVariable int id,@Valid @RequestBody Work_ScheduleDTO workScheduleDTO) throws Exception {
        workScheduleService.updateWork_Schedule(id,workScheduleDTO);
        return ResponseEntity.ok("Cập nhật Work_Schedule");
    }

    @DeleteMapping("/delete/{id}") //    http://localhost:2330/VOX/workschedule/delete
    public ResponseEntity<String> deleteWork_Schedule(@PathVariable int id) {
        workScheduleService.deleteWork_Schedule(id);
        return ResponseEntity.status(HttpStatus.OK).body("Đã xóa thành công Work_Schedule "+ id);
    }
}
