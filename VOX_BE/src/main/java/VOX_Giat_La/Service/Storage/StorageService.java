package VOX_Giat_La.Service.Storage;

import VOX_Giat_La.DTO.StorageDTO;
import VOX_Giat_La.Models.Storage;
import VOX_Giat_La.Repositories.StorageRepos;
import VOX_Giat_La.Respones.Storage.StorageRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService implements IStorageService{
    private final StorageRepos storageRepos;

    @Override
    public Storage createStorage(StorageDTO storageDTO) throws Exception {
        String position = storageDTO.getStoragePosition();
        if(storageRepos.existsByStoragePosition(position)){
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
    public Page<StorageRespone> getListStorage(PageRequest pageRequest) {
        return storageRepos.findAll(pageRequest).map(storage -> StorageRespone.fromStorage(storage));
    }
}
