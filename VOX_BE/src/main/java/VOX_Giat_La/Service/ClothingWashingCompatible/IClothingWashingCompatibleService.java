package VOX_Giat_La.Service.ClothingWashingCompatible;

import VOX_Giat_La.DTO.ClothingWashingCompatibleDTO;
import VOX_Giat_La.Models.ClothingWashingCompatible;

import java.util.List;

public interface IClothingWashingCompatibleService {
    ClothingWashingCompatible createClothingWashingCompatible(ClothingWashingCompatibleDTO clothingWashingCompatibleDTO) throws Exception;
    ClothingWashingCompatible getClothingWashingCompatiblebyID(int id);
    ClothingWashingCompatible updateClothingWashingCompatible(int id, ClothingWashingCompatibleDTO clothingWashingCompatibleDTO) throws Exception;
    void deleteClothingWashingCompatible(int id);
    List<ClothingWashingCompatible> getListClothingWashingCompatible();
}
