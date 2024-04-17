package VOX_Giat_La.Service.Bill;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.Models.Bill;

import java.util.List;

public interface IBillService {
    Bill createBill(BillDTO billDTO);
    Bill getBillByID(int billID);
    Bill updateBill(int id, BillDTO billDTO);
    void deleteBill(int id);
    List<Bill> getListBill();
}
