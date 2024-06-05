package VOX_Giat_La.Service.Washing_Method;

import VOX_Giat_La.DTO.Washing_MethodDTO;
import VOX_Giat_La.Models.Washing_Method;
import VOX_Giat_La.Repositories.Washing_MethodRepos;
import VOX_Giat_La.Respones.Washing_Method.Washing_MethodRespone;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Washing_MethodService implements IWashing_MethodService{
    private final Washing_MethodRepos washingMethodRepos;
    private final ModelMapper modelMapper;
    @Override
    public Washing_Method createWashing_Method(Washing_MethodDTO washingMethodDTO) {
        modelMapper.typeMap(Washing_MethodDTO.class,Washing_Method.class).addMappings(modelMapper -> modelMapper.skip(Washing_Method::setWashID));
        Washing_Method washingMethod = new Washing_Method();
        modelMapper.map(washingMethodDTO, washingMethod );
        washingMethod.setWashCreateDate(new Date());
        return washingMethodRepos.save(washingMethod);

    }

    @Override
    public Washing_Method getWashing_MethodbyID(int id) {
        return washingMethodRepos.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy kiểu giặt"));
    }

    @Override
    public Washing_Method updateWashing_Method(int id, Washing_MethodDTO washingMethodDTO) {
        modelMapper.typeMap(Washing_MethodDTO.class,Washing_Method.class).addMappings(modelMapper -> modelMapper.skip(Washing_Method::setWashID));
        Washing_Method washingMethod = washingMethodRepos.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy kiểu giặt"));
        modelMapper.map(washingMethodDTO, washingMethod );
        return washingMethodRepos.saveAndFlush(washingMethod);
    }

    @Override
    public void deleteWashing_Method(int id) {
        washingMethodRepos.deleteById(id);
    }

    @Override
    public Page<Washing_MethodRespone> getListWashing_Method(PageRequest pageRequest) {
        return washingMethodRepos.findAll(pageRequest).map(washingMethod -> Washing_MethodRespone.fromWashing_MethodRespone(washingMethod));
    }
}
