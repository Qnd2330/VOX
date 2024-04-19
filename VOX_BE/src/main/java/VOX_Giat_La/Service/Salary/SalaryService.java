package VOX_Giat_La.Service.Salary;

import VOX_Giat_La.DTO.SalaryDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Salary;
import VOX_Giat_La.Models.SalaryDetail;
import VOX_Giat_La.Models.Work_Schedule;
import VOX_Giat_La.Repositories.SalaryDetailRepos;
import VOX_Giat_La.Repositories.SalaryRepos;
import VOX_Giat_La.Repositories.Work_ScheduleRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryService implements ISalaryService{
    private final SalaryRepos salaryRepos;
    private final SalaryDetailRepos salaryDetailRepos;
    private final Work_ScheduleRepos workScheduleRepos;
    @Override
    public Salary createSalary(SalaryDTO salaryDTO) throws Exception {
        SalaryDetail salaryDetail = salaryDetailRepos.findById(salaryDTO.getSalaryDetailID()).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        Work_Schedule workSchedule = workScheduleRepos.findById(salaryDTO.getScheduleID()).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        Salary salary = Salary.builder()
                .salaryPerDay(salaryDTO.getSalaryPerDay())
                .salaryTotal(salaryDTO.getSalaryTotal())
                .build();
        salary.setSalaryDetail(salaryDetail);
        salary.setSchedule(workSchedule);
        return salaryRepos.save(salary);
    }

    @Override
    public Salary getSalaryByID(int id) throws Exception {
        Salary salary = salaryRepos.findById(id).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        return salary;
    }

    @Override
    public Salary updateSalary(int id, SalaryDTO salaryDTO) throws Exception {
        Salary  exitingsalary = salaryRepos.findById(id).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        SalaryDetail salaryDetail = salaryDetailRepos.findById(salaryDTO.getSalaryDetailID()).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        Work_Schedule workSchedule = workScheduleRepos.findById(salaryDTO.getScheduleID()).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        exitingsalary.setSalaryDetail(salaryDetail);
        exitingsalary.setSchedule(workSchedule);
        exitingsalary.setSalaryPerDay(salaryDTO.getSalaryPerDay());
        exitingsalary.setSalaryTotal(salaryDTO.getSalaryTotal());
        return salaryRepos.saveAndFlush(exitingsalary);
    }

    @Override
    public void deleteSalary(int id) {
        salaryRepos.deleteById(id);
    }

    @Override
    public List<Salary> getListSalary() {
        List<Salary> salary = salaryRepos.findAll();
        return salary;
    }

}
