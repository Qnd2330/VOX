package VOX_Giat_La.Service.BillDetails;

import VOX_Giat_La.DTO.BillDetailsDTO;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BillDetailsService implements IBillDetailsService{
    private final BillRepos billRepos;
    private final BillDetailsRepos billDetailsRepos;
    private final KindOfClothingRepos kindOfClothingRepos;
    private final Washing_MethodRepos washing_methodRepos;
    @Override
    public BillDetails createBillDetails(BillDetailsDTO billDetailsDTO) {
        Bill bill =  billRepos.findById(billDetailsDTO.getBillID()).orElseThrow(
                ()->new RuntimeException("Không tìm thấy bill với ID: "+billDetailsDTO.getBillID()));
        return null;
    }

    @Override
    public BillDetails getBillDetailsByID(int billDetailsID) {
        return billDetailsRepos.findById(billDetailsID).orElseThrow(()-> new RuntimeException("Không tìm thấy bill"));
    }

    @Override
    public BillDetails updateBillDetails(int id, BillDetailsDTO billDetailsDTO) {
        BillDetails billDetailsUpdate = getBillDetailsByID(id);
        billDetailsRepos.saveAndFlush(billDetailsUpdate);
        return billDetailsUpdate;
    }

    @Override
    public void deleteBillDetails(int id) {
        billDetailsRepos.deleteById(id);
    }

    @Override
    public List<BillDetails> getListBillDetails() {
        return billDetailsRepos.findAll();
    }
}
