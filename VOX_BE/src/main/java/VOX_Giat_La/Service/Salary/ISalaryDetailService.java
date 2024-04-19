package VOX_Giat_La.Service.Salary;

import VOX_Giat_La.DTO.SalaryDetailDTO;
import VOX_Giat_La.DTO.Work_ScheduleDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.SalaryDetail;
import VOX_Giat_La.Models.Work_Schedule;

import java.util.List;

public interface ISalaryDetailService {
    SalaryDetail createSalaryDetail(SalaryDetailDTO salaryDetailDTO) throws Exception;
    SalaryDetail getSalaryDetailByID(int id) throws Exception;
    SalaryDetail updateSalaryDetail(int id, SalaryDetailDTO salaryDetailDTO) throws Exception;
    void deleteSalaryDetail(int id);
    List<SalaryDetail> getListSalaryDetail();
}
