package VOX_Giat_La.Service.KindOfClothing;

import VOX_Giat_La.DTO.KindOfClothingDTO;;
import VOX_Giat_La.Models.KindOfClothing;

import java.util.List;

public interface IKindOfClothingService {
    KindOfClothing createKindOfClothing(KindOfClothingDTO kindOfClothingDTO);
    KindOfClothing getKindOfClothingbyID(int id);
    KindOfClothing updateKindOfClothing(int id, KindOfClothingDTO kindOfClothingDTO);
    void deleteKindOfClothing(int id);
    List<KindOfClothing> getListKindOfClothing();
}
