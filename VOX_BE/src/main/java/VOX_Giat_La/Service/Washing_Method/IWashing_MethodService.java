package VOX_Giat_La.Service.Washing_Method;

import VOX_Giat_La.DTO.BillDTO;
import VOX_Giat_La.DTO.Washing_MethodDTO;
import VOX_Giat_La.Models.Bill;
import VOX_Giat_La.Models.Washing_Method;

import java.util.List;

public interface IWashing_MethodService {
    Washing_Method createWashing_Method(Washing_MethodDTO washingMethodDTO);
    Washing_Method getWashing_MethodbyID(int id);
    Washing_Method updateWashing_Method(int id, Washing_MethodDTO washingMethodDTO);
    void deleteWashing_Method(int id);
    List<Washing_Method> getListWashing_Method();
}
