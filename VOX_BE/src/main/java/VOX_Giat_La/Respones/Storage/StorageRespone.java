package VOX_Giat_La.Respones.Storage;

import VOX_Giat_La.Models.Storage;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageRespone {
    private int storageID;
    private String storagePosition;
    private Boolean storageStatus;

    private LocalDateTime storageCreateDate;

    public static StorageRespone  fromStorage (Storage storage) {
        StorageRespone storageRespone = StorageRespone.builder()
                .storageID(storage.getStorageID())
                .storagePosition(storage.getStoragePosition())
                .storageStatus(storage.getStorageStatus())
                .storageCreateDate(storage.getStorageCreateDate())
                .build();
        return  storageRespone;
    }
}
