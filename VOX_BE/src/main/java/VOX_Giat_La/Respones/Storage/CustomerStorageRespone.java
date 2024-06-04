package VOX_Giat_La.Respones.Storage;

import VOX_Giat_La.Models.CustomerStorage;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerStorageRespone {
    private int cusItemID;
    private String storagePosition;
    private int billDetailID;
    private Boolean cusStoredStatus;

    public static CustomerStorageRespone fromCustomerStorage(CustomerStorage customerStorage) {
        CustomerStorageRespone customerStorageRespone = CustomerStorageRespone.builder()
                .cusItemID(customerStorage.getCusItemID())
                .storagePosition(customerStorage.getStorage().getStoragePosition())
                .billDetailID(customerStorage.getBillDetail().getBillDetailID())
                .cusStoredStatus(customerStorage.getCusStoredStatus())
                .build();
        return customerStorageRespone;
    }
}
