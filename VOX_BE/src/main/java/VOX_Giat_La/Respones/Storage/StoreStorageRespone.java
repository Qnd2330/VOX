package VOX_Giat_La.Respones.Storage;

import VOX_Giat_La.Models.StoreStorage;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreStorageRespone {
    private int storeItemID;
    private String storagePosition;
    private String storeItemName;
    private int storeItemQuantity;
    private float storeItemCost;
    private Boolean storeItemStatus;

    public static  StoreStorageRespone fromStoreStorageRespone (StoreStorage storeStorage) {
        StoreStorageRespone storageRespone = StoreStorageRespone.builder()
                .storeItemID(storeStorage.getStoreItemID())
                .storagePosition(storeStorage.getStorage().getStoragePosition())
                .storeItemName(storeStorage.getStoreItemName())
                .storeItemQuantity(storeStorage.getStoreItemQuantity())
                .storeItemCost(storeStorage.getStoreItemCost())
                .storeItemStatus(storeStorage.getStoreItemStatus())
                .build();
        return  storageRespone;

    }

}
