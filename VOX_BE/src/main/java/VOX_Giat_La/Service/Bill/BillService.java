package VOX_Giat_La.Service.Bill;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Repositories.BillRepos;
import VOX_Giat_La.Repositories.UserRepos;
import VOX_Giat_La.Respones.Bill.BillRespones;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final UserRepos userRepos;
    private final BillRepos billRepos;

    @Override
    @Transactional
    public Bill createBill(BillDTO billDTO) throws DataNotFoundException {
        User user =  userRepos.findById(billDTO.getUserID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy user với ID: "+billDTO.getUserID()));
        Bill newBill = Bill.builder()
                .user(user)
                .billDescription(billDTO.getBillDescription())
                .sumWeight(billDTO.getSumWeight())
                .cost(billDTO.getCost())
                .billStatus(false)
                .build();
        return billRepos.save(newBill);
    }

    @Override
    public BillRespones getBillResponesByID(int billID) throws DataNotFoundException {
        return BillRespones.fromBill( billRepos.findById(billID).orElseThrow(()-> new DataNotFoundException("Không tìm thấy bill")));
    }

    @Override
    public Bill getBillByID(int billID) throws DataNotFoundException {
        return billRepos.findById(billID).orElseThrow(()-> new DataNotFoundException("Không tìm thấy bill"));
    }

    @Override
    public Page<BillRespones> getListBill(PageRequest pageRequest) {
        return billRepos.findAll(pageRequest).map(bill -> BillRespones.fromBill(bill));
    }

    @Override
    public Bill updateImge(int id, BillDTO billDTO) throws Exception {
        Bill billUpdate = getBillByID(id);
        billUpdate.setImage(String.valueOf(billDTO.getImage()));
        return billRepos.saveAndFlush(billUpdate);
    }

    @Override
    public Bill getBillByUserId(int userID) throws Exception{
         User user = userRepos.findById(userID).orElseThrow(()->new DataNotFoundException("Không tìm thấy user với ID: "+ userID));
        return billRepos.findByUser(user);
    }

    @Override
    public Bill updateBill(int id, BillDTO billDTO) throws Exception{
        Bill billUpdate = getBillByID(id);
        User user =  userRepos.findById(billDTO.getUserID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy user với ID: "+billDTO.getUserID()));
        billUpdate.setUser(user);
        billUpdate.setBillDescription(billDTO.getBillDescription());
        billUpdate.setSumWeight(billDTO.getSumWeight());
        billUpdate.setCost(billDTO.getCost());
        billUpdate.setBillPayDate(billDTO.getBillPayDate());
        billUpdate.setBillStatus(billDTO.getBillStatus());
        return billRepos.saveAndFlush(billUpdate);
    }


    @Override
    public void deleteBill(int id) {
        Optional<Bill> optionalBill = billRepos.findById(id);
        optionalBill.ifPresent(billRepos::delete);
    }

}
