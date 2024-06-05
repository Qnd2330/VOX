package VOX_Giat_La.Service.StoreStorage;

import VOX_Giat_La.DTO.StoreStorageDTO;
import VOX_Giat_La.Models.StoreStorage;
import VOX_Giat_La.Repositories.StoreStorageRepos;
import VOX_Giat_La.Respones.Storage.StoreStorageRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IStoreStorageService {
    StoreStorage createStoreStorage(StoreStorageDTO storeStorageDTO) throws Exception;
    StoreStorage getStoreStorageByID(int id) throws Exception;
    StoreStorage updateStoreStorage(int id, StoreStorageDTO storeStorageDTO) throws Exception;
    void deleteStoreStorage(int id);
    Page<StoreStorageRespone> getListStoreStorage(PageRequest pageRequest);
}
