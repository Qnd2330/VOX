package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailsRepos extends JpaRepository<BillDetails, Integer> {
    List<BillDetails> findByBill(Bill bill);
}
