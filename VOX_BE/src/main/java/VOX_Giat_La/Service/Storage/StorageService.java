package VOX_Giat_La.Service.Storage;

import VOX_Giat_La.DTO.StorageDTO;
import VOX_Giat_La.Models.ClothingWashingCompatible;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Repositories.StorageRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService{
    private final StorageRepos storageRepos;

    @Override
    public Storage createStorage(StorageDTO storageDTO) throws Exception {
        String position = storageDTO.getStoragePosition();
        if(storageRepos.existsByPosition(position)){
            throw  new DataIntegrityViolationException("Vị trí đã tồn tại");
        }
        Storage newStorage = Storage.builder()
                .storagePosition(storageDTO.getStoragePosition())
                .storageStatus(storageDTO.getStorageStatus())
                .build();
        return storageRepos.save(newStorage);
    }

    @Override
    public Storage getStoragebyID(int id) {
        return storageRepos.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy vị trí kho"));
    }

    @Override
    public Storage updateStorage(int id, StorageDTO storageDTO) throws Exception {
        Storage newStorage = getStoragebyID(id);
        newStorage.setStoragePosition(storageDTO.getStoragePosition());
        newStorage.setStorageStatus(storageDTO.getStorageStatus());
        return storageRepos.saveAndFlush(newStorage);
    }

    @Override
    public void deleteStorage(int id) {
    }

    @Override
    public List<Storage> getListStorage() {
        return storageRepos.findAll();
    }
}
