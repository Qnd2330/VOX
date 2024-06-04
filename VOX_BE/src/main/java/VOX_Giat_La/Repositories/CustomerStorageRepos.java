package VOX_Giat_La.Repositories;

import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Models.CustomerStorage;
import VOX_Giat_La.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerStorageRepos extends JpaRepository<CustomerStorage, Integer> {
    CustomerStorage findByBillDetail(BillDetails billDetails);
}
