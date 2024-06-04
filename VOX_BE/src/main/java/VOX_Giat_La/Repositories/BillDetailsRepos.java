package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.BillDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailsRepos extends JpaRepository<BillDetails, Integer> {
    Page<BillDetails> findByBill(Bill bill, Pageable pageable);
}
