package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepos extends JpaRepository<Bill, Integer> {
}
