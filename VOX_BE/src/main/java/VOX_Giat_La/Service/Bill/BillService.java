package VOX_Giat_La.Service.Bill;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Repositories.BillRepos;
import VOX_Giat_La.Repositories.UserRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final UserRepos userRepos;
    private final BillRepos billRepos;

    @Override
    public Bill createBill(BillDTO billDTO)  {
        User user =  userRepos.findById(billDTO.getUserID()).orElseThrow(
                ()->new RuntimeException("Không tìm thấy user với ID: "+billDTO.getUserID()));
        return null;
    }

    @Override
    public Bill getBillByID(int billID) {
        return billRepos.findById(billID).orElseThrow(()-> new RuntimeException("Không tìm thấy bill"));
    }

    @Override
    public List<Bill> getListBill() {
        return billRepos.findAll();
    }

    @Override
    public Bill updateBill(int id, BillDTO billDTO) {
        Bill billUpdate = getBillByID(id);
        billRepos.saveAndFlush(billUpdate);
        return billUpdate;
    }

    @Override
    public void deleteBill(int id) {
        billRepos.deleteById(id);
    }
}
