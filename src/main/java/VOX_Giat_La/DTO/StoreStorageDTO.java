package VOX_Giat_La.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreStorageDTO {
    @NotNull
    private int storageID;
    @NotNull
    private String storeItemName;
    private String storeItemDescription;
    @Min(0)
    private int storeItemQuantity;
    @Min(0)
    private float storeItemCost;
    private String storeItemPicture;
    private Date storeItemDayIn;
    private Boolean storeItemStatus;
}
