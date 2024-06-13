package VOX_Giat_La.Service.Washing_Method;

import VOX_Giat_La.DTO.Washing_MethodDTO;
import VOX_Giat_La.Models.Washing_Method;
import VOX_Giat_La.Respones.Washing_Method.Washing_MethodRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IWashing_MethodService {
    Washing_Method createWashing_Method(Washing_MethodDTO washingMethodDTO);
    Washing_MethodRespone getWashing_MethodbyID(int id);
    Washing_Method updateWashing_Method(int id, Washing_MethodDTO washingMethodDTO);
    void deleteWashing_Method(int id);
    Page<Washing_MethodRespone> getListWashing_Method(PageRequest pageRequest);
}
