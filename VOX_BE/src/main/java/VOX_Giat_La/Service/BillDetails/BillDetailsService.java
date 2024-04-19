package VOX_Giat_La.Service.BillDetails;

import VOX_Giat_La.DTO.BillDetailsDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.*;
import VOX_Giat_La.Repositories.*;
import VOX_Giat_La.Respones.BillDetailRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillDetailsService implements IBillDetailsService{
    private final BillRepos billRepos;
    private final BillDetailsRepos billDetailsRepos;

    private final Washing_MethodRepos washing_methodRepos;
    @Override
    public BillDetails createBillDetails( int billID, BillDetailsDTO billDetailsDTO) throws DataNotFoundException {
        Bill Exitingbill =  billRepos.findById(billID).orElseThrow(()->new DataNotFoundException("Không tìm thấy bill với ID: "+billDetailsDTO.getBillID()));
        Washing_Method ExitingWash = washing_methodRepos.findById(billDetailsDTO.getWashID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy kiểu giặt với ID: "+billDetailsDTO.getWashID()));

        BillDetails newBillDetails = BillDetails.builder()
                .bill(Exitingbill)
                .wash(ExitingWash)

                .description(billDetailsDTO.getDescription())
                .weight(billDetailsDTO.getWeight())
                .billDetailStatus(false)
                .price(ExitingWash.getWashCost())
                .build();
        return billDetailsRepos.save(newBillDetails);
    }

    @Override
    public BillDetails getBillDetailsByID(int billDetailsID) {
        return billDetailsRepos.findById(billDetailsID).orElseThrow(()-> new RuntimeException("Không tìm thấy bill"));
    }

    @Override
    public  List<BillDetails> getBillDetailsByBill(int billID) {
        Bill bill =  billRepos.findById(billID).orElseThrow(()->new RuntimeException("Không tìm thấy bill với ID: "+ billID));
        return billDetailsRepos.findByBill(bill);
    }

    @Override
    public BillDetails updateBillDetails(int id, BillDetailsDTO billDetailsDTO) throws DataNotFoundException {
        BillDetails billDetailsUpdate = getBillDetailsByID(id);
        Bill Exitingbill =  billRepos.findById(billDetailsDTO.getBillID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy bill với ID: "+billDetailsDTO.getBillID()));
        Washing_Method ExitingWash = washing_methodRepos.findById(billDetailsDTO.getWashID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy kiểu giặt với ID: "+billDetailsDTO.getWashID()));

            billDetailsUpdate.setBill(Exitingbill);
            billDetailsUpdate.setWash(ExitingWash);

            billDetailsUpdate.setDescription(billDetailsDTO.getDescription());
            billDetailsUpdate.setWeight(billDetailsDTO.getWeight());
            billDetailsUpdate.setPrice(billDetailsDTO.getPrice());
            billDetailsUpdate.setBillDetailStatus(billDetailsDTO.getBillDetailStatus());
        return billDetailsRepos.saveAndFlush(billDetailsUpdate);
    }

    @Override
    public void deleteBillDetails(int id) {
        Optional<BillDetails> optionalBillDetails = billDetailsRepos.findById(id);
        optionalBillDetails.ifPresent(billDetailsRepos::delete);
    }

    @Override
    public Page<BillDetailRespone> getListBillDetails(PageRequest pageRequest) {
        return billDetailsRepos.findAll(pageRequest).map(billDetails -> BillDetailRespone.fromBillDetail(billDetails));
    }
}
