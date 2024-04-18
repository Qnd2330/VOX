package VOX_Giat_La.Service.ClothingWashingCompatible;


import VOX_Giat_La.DTO.ClothingWashingCompatibleDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.*;
import VOX_Giat_La.Repositories.ClothingWashingCompatibleRepos;
import VOX_Giat_La.Repositories.KindOfClothingRepos;
import VOX_Giat_La.Repositories.RolesRepos;
import VOX_Giat_La.Repositories.Washing_MethodRepos;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClothingWashingCompatibleService implements IClothingWashingCompatibleService{
    private final ClothingWashingCompatibleRepos clothingWashingCompatibleRepos;
    private final KindOfClothingRepos kindOfClothingRepos;
    private final Washing_MethodRepos washingMethodRepos;
    private final ModelMapper modelMapper;
    @Override
    public ClothingWashingCompatible createClothingWashingCompatible(ClothingWashingCompatibleDTO clothingWashingCompatibleDTO) throws Exception {

        ClothingWashingCompatible newClothingWashingCompatible = ClothingWashingCompatible.builder()
                .compatibility(clothingWashingCompatibleDTO.getCompatibility())
                .build();
        KindOfClothing kindOfClothing = kindOfClothingRepos.findById(clothingWashingCompatibleDTO.getClothID()).orElseThrow(()-> new DataNotFoundException("Không tìm thấy loại Quần áo "));
        newClothingWashingCompatible.setCloth(kindOfClothing);
        Washing_Method washingMethod = washingMethodRepos.findById(clothingWashingCompatibleDTO.getWashID()).orElseThrow(()-> new DataNotFoundException("Không tìm thấy loại kiểu giặt "));
        newClothingWashingCompatible.setWash(washingMethod);

        return clothingWashingCompatibleRepos.save(newClothingWashingCompatible);
    }

    @Override
    public ClothingWashingCompatible getClothingWashingCompatiblebyID(int id) {
        return clothingWashingCompatibleRepos.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy kiểu giặt hợp kiểu áo"));
    }

    @Override
    public ClothingWashingCompatible updateClothingWashingCompatible(int id, ClothingWashingCompatibleDTO clothingWashingCompatibleDTO) throws Exception {
        ClothingWashingCompatible newClothingWashingCompatible = getClothingWashingCompatiblebyID(id);
        KindOfClothing kindOfClothing = kindOfClothingRepos.findById(clothingWashingCompatibleDTO.getClothID()).orElseThrow(()-> new DataNotFoundException("Không tìm thấy loại Quần áo "));
        newClothingWashingCompatible.setCloth(kindOfClothing);
        Washing_Method washingMethod = washingMethodRepos.findById(clothingWashingCompatibleDTO.getWashID()).orElseThrow(()-> new DataNotFoundException("Không tìm thấy loại kiểu giặt "));
        newClothingWashingCompatible.setWash(washingMethod);
        newClothingWashingCompatible.setCompatibility(clothingWashingCompatibleDTO.getCompatibility());
        return clothingWashingCompatibleRepos.saveAndFlush(newClothingWashingCompatible);
    }

    @Override
    public void deleteClothingWashingCompatible(int id) {
        clothingWashingCompatibleRepos.deleteById(id);
    }

    @Override
    public List<ClothingWashingCompatible> getListClothingWashingCompatible() {
        return clothingWashingCompatibleRepos.findAll();
    }
}
