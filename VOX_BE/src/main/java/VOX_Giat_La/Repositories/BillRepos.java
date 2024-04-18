package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepos extends JpaRepository<Bill, Integer> {
    Bill findByUser(User user);
}
