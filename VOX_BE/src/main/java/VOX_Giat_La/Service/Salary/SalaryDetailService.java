package VOX_Giat_La.Service.Salary;

import VOX_Giat_La.DTO.SalaryDTO;
import VOX_Giat_La.DTO.SalaryDetailDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Salary;
import VOX_Giat_La.Models.SalaryDetail;
import VOX_Giat_La.Models.Work_Schedule;
import VOX_Giat_La.Repositories.SalaryDetailRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryDetailService implements ISalaryDetailService{
    private final SalaryDetailRepos salaryDetailRepos;

    @Override
    public SalaryDetail createSalaryDetail(SalaryDetailDTO salaryDetailDTO) throws Exception {
        SalaryDetail newSalaryDetail = SalaryDetail.builder()
                .salaryDetailName(salaryDetailDTO.getSalaryDetailName())
                .salaryCountType(salaryDetailDTO.getSalaryCountType())
                .salaryValue(salaryDetailDTO.getSalaryValue())
                .build();
        return salaryDetailRepos.save(newSalaryDetail);
    }

    @Override
    public SalaryDetail getSalaryDetailByID(int id) throws Exception {
        SalaryDetail salaryDetail= salaryDetailRepos.findById(id).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        return salaryDetail;
    }

    @Override
    public SalaryDetail updateSalaryDetail(int id, SalaryDetailDTO salaryDetailDTO) throws Exception {
        SalaryDetail exitingSalaryDetail = salaryDetailRepos.findById(id).orElseThrow(()-> new DataNotFoundException("không tìm thấy !"));
        exitingSalaryDetail.setSalaryDetailName(salaryDetailDTO.getSalaryDetailName());
        exitingSalaryDetail.setSalaryCountType(salaryDetailDTO.getSalaryCountType());
        exitingSalaryDetail.setSalaryValue(salaryDetailDTO.getSalaryValue());
        return  salaryDetailRepos.saveAndFlush(exitingSalaryDetail);
    }

    @Override
    public void deleteSalaryDetail(int id) {
        salaryDetailRepos.deleteById(id);
    }

    @Override
    public List<SalaryDetail> getListSalaryDetail() {
        List<SalaryDetail> salaryDetails = salaryDetailRepos.findAll();
        return salaryDetails;
    }
}
