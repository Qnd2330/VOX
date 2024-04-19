package VOX_Giat_La.Service.Work_Schedule;

import VOX_Giat_La.DTO.Work_ScheduleDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Models.Work_Schedule;
import VOX_Giat_La.Repositories.UserRepos;
import VOX_Giat_La.Repositories.Work_ScheduleRepos;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class Work_ScheduleService implements IWork_ScheduleService{
    private final Work_ScheduleRepos workScheduleRepos;
    private final UserRepos userRepos;
    @Override
    public Work_Schedule createWork_Schedule(Work_ScheduleDTO workScheduleDTO) throws Exception {
        User exitinguser = userRepos.findById(workScheduleDTO.getUserID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy user"+ workScheduleDTO.getUserID()));
        Work_Schedule newWorkSchedule = Work_Schedule.builder()
                .workDate(workScheduleDTO.getWorkDate())
                .startTime(workScheduleDTO.getStartTime())
                .endTime(workScheduleDTO.getEndTime())
                .shiftType(workScheduleDTO.getShiftType())
                .build();
            newWorkSchedule.setUser(exitinguser);
        return workScheduleRepos.save(newWorkSchedule);
    }

    @Override
    public Work_Schedule getWork_ScheduleByID(int id) throws Exception {
        Work_Schedule workSchedule = workScheduleRepos.findById(id).orElseThrow(()-> new DataNotFoundException("Không tìm thấy Lịch làm với id: "+ id));
        return workSchedule;
    }

    @Override
    public List<Work_Schedule> getWork_ScheduleByUser(int userID) throws DataNotFoundException {
        User user =  userRepos.findById(userID).orElseThrow(()->new DataNotFoundException("Không tìm thấy user"+ userID));
        List<Work_Schedule> workSchedules = workScheduleRepos.findByUser(user);
        return workSchedules;
    }

    @Override
    public Work_Schedule updateWork_Schedule(int id, Work_ScheduleDTO workScheduleDTO) throws Exception {
        Work_Schedule exitingWorkSchedule = workScheduleRepos.findById(id).orElseThrow(()-> new DataNotFoundException("Không tìm thấy lịch làm"));
        return exitingWorkSchedule;
    }

    @Override
    public void deleteWork_Schedule(int id) {
        workScheduleRepos.deleteById(id);
    }

    @Override
    public List<Work_Schedule> getListWork_Schedule() {
        return workScheduleRepos.findAll();
    }
}
