package VOX_Giat_La.Service.BillDetails;

import VOX_Giat_La.DTO.BillDetailsDTO;
import VOX_Giat_La.Models.BillDetails;

import java.util.List;

public interface IBillDetailsService {
    BillDetails createBillDetails(BillDetailsDTO billDetailsDTO);
    BillDetails getBillDetailsByID(int billDetailsID);
    BillDetails updateBillDetails(int id, BillDetailsDTO billDetailsDTO);
    void deleteBillDetails(int id);
    List<BillDetails> getListBillDetails();
}
