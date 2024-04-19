package VOX_Giat_La.Service.Work_Schedule;

import VOX_Giat_La.DTO.StoreStorageDTO;
import VOX_Giat_La.DTO.Work_ScheduleDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.StoreStorage;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Models.Work_Schedule;

import java.util.List;

public interface IWork_ScheduleService {
    Work_Schedule createWork_Schedule(Work_ScheduleDTO workScheduleDTO) throws Exception;
    Work_Schedule getWork_ScheduleByID(int id) throws Exception;
    List<Work_Schedule> getWork_ScheduleByUser (int userID) throws DataNotFoundException;
    Work_Schedule updateWork_Schedule(int id, Work_ScheduleDTO workScheduleDTO) throws Exception;
    void deleteWork_Schedule(int id);
    List<Work_Schedule> getListWork_Schedule();
}
