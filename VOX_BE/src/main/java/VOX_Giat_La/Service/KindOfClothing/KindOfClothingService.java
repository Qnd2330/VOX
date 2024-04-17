package VOX_Giat_La.Service.KindOfClothing;

import VOX_Giat_La.DTO.KindOfClothingDTO;
import VOX_Giat_La.DTO.Washing_MethodDTO;
import VOX_Giat_La.Models.KindOfClothing;
import VOX_Giat_La.Models.Washing_Method;
import VOX_Giat_La.Repositories.KindOfClothingRepos;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KindOfClothingService implements IKindOfClothingService {
    private final KindOfClothingRepos kindOfClothingRepos;
    private final ModelMapper modelMapper;
    @Override
    public KindOfClothing createKindOfClothing(KindOfClothingDTO kindOfClothingDTO) {
        modelMapper.typeMap(KindOfClothingDTO.class, KindOfClothing.class).addMappings(modelMapper -> modelMapper.skip(KindOfClothing::setClothesID));
        KindOfClothing kindOfClothing = new KindOfClothing();
        modelMapper.map(kindOfClothingDTO, kindOfClothing );
        return kindOfClothingRepos.save(kindOfClothing);
    }

    @Override
    public KindOfClothing getKindOfClothingbyID(int id) {
        return kindOfClothingRepos.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy loại quần áo"));
    }

    @Override
    public KindOfClothing updateKindOfClothing(int id, KindOfClothingDTO kindOfClothingDTO) {
        modelMapper.typeMap(KindOfClothingDTO.class, KindOfClothing.class).addMappings(modelMapper -> modelMapper.skip(KindOfClothing::setClothesID));
        KindOfClothing kindOfClothing = kindOfClothingRepos.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy kiểu giặt"));
        modelMapper.map(kindOfClothingDTO, kindOfClothing );
        return kindOfClothingRepos.saveAndFlush(kindOfClothing);
    }

    @Override
    public void deleteKindOfClothing(int id) {
        kindOfClothingRepos.deleteById(id);
    }

    @Override
    public List<KindOfClothing> getListKindOfClothing() {
        return kindOfClothingRepos.findAll();
    }
}
