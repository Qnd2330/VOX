package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Respones.BillDetailRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailsRepos extends JpaRepository<BillDetails, Integer> {
    Page<BillDetails> findByBill(Bill bill, Pageable pageable);
}
