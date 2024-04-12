package VOX_Giat_La.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreStorageDTO {
    @NotNull
    private int storageID;
    @NotBlank
    private String storeItemName;
    @Size(min = 0, message = "")
    @Size(max = 2000, message = "Tối đa là 2000 chữ cái ")
    private String storeItemDescription;
    @Min(0)
    private int storeItemQuantity;
    @Min(0)
    private float storeItemCost;
    private MultipartFile storeItemPicture;
    private Date storeItemDayIn;
    private Boolean storeItemStatus;
}
