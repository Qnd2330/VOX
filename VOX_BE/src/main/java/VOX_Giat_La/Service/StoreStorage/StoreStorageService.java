package VOX_Giat_La.Service.StoreStorage;

import VOX_Giat_La.DTO.StoreStorageDTO;
import VOX_Giat_La.Exeception.DataNotFoundException;
import VOX_Giat_La.Models.*;
import VOX_Giat_La.Repositories.BillDetailsRepos;
import VOX_Giat_La.Repositories.CustomerStorageRepos;
import VOX_Giat_La.Repositories.StorageRepos;
import VOX_Giat_La.Repositories.StoreStorageRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreStorageService implements IStoreStorageService{
    private final StorageRepos storageRepos;
    private final StoreStorageRepos storeStorageRepos;
    @Override
    public StoreStorage createStoreStorage(StoreStorageDTO storeStorageDTO) throws Exception {
        Storage storage =  storageRepos.findById(storeStorageDTO.getStorageID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy vị trí kho "+ storeStorageDTO.getStorageID()));
        StoreStorage newStoreStorage = StoreStorage.builder()
                .storage(storage)
                .storeItemName(storeStorageDTO.getStoreItemName())
                .storeItemDescription(storeStorageDTO.getStoreItemDescription())
                .storeItemQuantity(storeStorageDTO.getStoreItemQuantity())
                .storeItemCost(storeStorageDTO.getStoreItemCost())
                .storeItemPicture(String.valueOf(storeStorageDTO.getStoreItemPicture()))
                .storeItemStatus(storeStorageDTO.getStoreItemStatus())
                .build();
        return storeStorageRepos.save(newStoreStorage);
    }

    @Override
    public StoreStorage getStoreStorageByID(int storeItemID) throws Exception {
        return storeStorageRepos.findById(storeItemID).orElseThrow(()-> new DataNotFoundException("Không tìm thấy store storage"));
    }

    @Override
    public StoreStorage updateStoreStorage(int id, StoreStorageDTO storeStorageDTO) throws Exception {
        StoreStorage storeStorageUpdate = getStoreStorageByID(id);
        Storage storage =  storageRepos.findById(storeStorageDTO.getStorageID()).orElseThrow(()->new DataNotFoundException("Không tìm thấy vị trí kho "+ storeStorageDTO.getStorageID()));
        storeStorageUpdate.setStorage(storage);
        storeStorageUpdate.setStoreItemName(storeStorageDTO.getStoreItemName());
        storeStorageUpdate.setStoreItemDescription(storeStorageDTO.getStoreItemDescription());
        storeStorageUpdate.setStoreItemQuantity(storeStorageDTO.getStoreItemQuantity());
        storeStorageUpdate.setStoreItemCost(storeStorageDTO.getStoreItemCost());
        storeStorageUpdate.setStoreItemPicture(String.valueOf(storeStorageDTO.getStoreItemPicture()));
        storeStorageUpdate.setStoreItemStatus(storeStorageDTO.getStoreItemStatus());
        return storeStorageRepos.saveAndFlush(storeStorageUpdate);
    }

    @Override
    public void deleteStoreStorage(int id) {
        Optional<StoreStorage> optionalStoreStorage = storeStorageRepos.findById(id);
        optionalStoreStorage.ifPresent(storeStorageRepos::delete);
    }

    @Override
    public List<StoreStorage> getListStoreStorage() {
        return storeStorageRepos.findAll();
    }
}
