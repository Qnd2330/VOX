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
public class CustomerStorageDTO {
    @NotNull
    private int storageID;
    @NotNull
    private int billDetailID;
    private String itemDescription;
    private String itemPicture;
    private Date storedDateStart;
    private Date storedDateEnd;
    private Boolean cusStoredStatus;
}
