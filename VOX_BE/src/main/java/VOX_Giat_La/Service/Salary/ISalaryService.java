package VOX_Giat_La.Service.Salary;

import VOX_Giat_La.DTO.SalaryDTO;
import VOX_Giat_La.DTO.Work_ScheduleDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Salary;
import VOX_Giat_La.Models.Work_Schedule;

import java.util.List;

public interface ISalaryService {
    Salary createSalary(SalaryDTO salaryDTO) throws Exception;
    Salary getSalaryByID(int id) throws Exception;
    Salary updateSalary(int id, SalaryDTO salaryDTO) throws Exception;
    void deleteSalary(int id);
    List<Salary> getListSalary();
}
