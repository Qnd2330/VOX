package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Models.Work_Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Work_ScheduleRepos extends JpaRepository<Work_Schedule, Integer> {
    List<Work_Schedule> findByUser(User user);
}
