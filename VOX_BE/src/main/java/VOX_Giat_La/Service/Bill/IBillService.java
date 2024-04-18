package VOX_Giat_La.Service.Bill;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.User;
import VOX_Giat_La.Respones.BillRespones;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IBillService {
    Bill createBill(BillDTO billDTO) throws Exception;
    Bill getBillByID(int billID) throws Exception;
    Bill updateBill(int id, BillDTO billDTO) throws  Exception;
    void deleteBill(int id);
    Page<BillRespones> getListBill(PageRequest pageRequest);
    Bill updateImge(int id, BillDTO billDTO) throws Exception;

    Bill getBillByUserId(int UserID) throws Exception;



}
