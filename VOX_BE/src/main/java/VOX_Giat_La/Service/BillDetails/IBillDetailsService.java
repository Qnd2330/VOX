package VOX_Giat_La.Service.BillDetails;

import VOX_Giat_La.DTO.BillDetailsDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.BillDetails;
import VOX_Giat_La.Respones.Bill.BillDetailListRespone;
import VOX_Giat_La.Respones.Bill.BillDetailRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBillDetailsService {
    BillDetails createBillDetails(int billID, BillDetailsDTO billDetailsDTO) throws DataNotFoundException;
    BillDetails getBillDetailsByID(int billDetailsID);
    BillDetailListRespone getBillDetailsByBill(int billID, int page, int size);
    BillDetails updateBillDetails(int id, BillDetailsDTO billDetailsDTO) throws DataNotFoundException;
    void deleteBillDetails(int id);
    Page<BillDetailRespone> getListBillDetails(PageRequest pageRequest);
}
