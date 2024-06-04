package VOX_Giat_La.Service.Bill;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Respones.Bill.BillRespones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBillService {
    Bill createBill(BillDTO billDTO) throws Exception;
    BillRespones getBillResponesByID(int billID) throws Exception;
    Bill getBillByID(int billID) throws Exception;
    Bill updateBill(int id, BillDTO billDTO) throws  Exception;
    void deleteBill(int id);
    Page<BillRespones> getListBill(PageRequest pageRequest);
    Bill updateImge(int id, BillDTO billDTO) throws Exception;

    Bill getBillByUserId(int UserID) throws Exception;



}
