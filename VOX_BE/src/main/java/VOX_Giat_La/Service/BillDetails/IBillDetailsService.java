package VOX_Giat_La.Service.BillDetails;

import VOX_Giat_La.DTO.BillDetailsDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Respones.BillDetailRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IBillDetailsService {
    BillDetails createBillDetails(int billID, BillDetailsDTO billDetailsDTO) throws DataNotFoundException;
    BillDetails getBillDetailsByID(int billDetailsID);
    List<BillDetails> getBillDetailsByBill(int billID);
    BillDetails updateBillDetails(int id, BillDetailsDTO billDetailsDTO) throws DataNotFoundException;
    void deleteBillDetails(int id);
    Page<BillDetailRespone> getListBillDetails(PageRequest pageRequest);
}
