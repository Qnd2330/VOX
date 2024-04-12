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
public class CustomerStorageDTO {
    @NotNull
    private int storageID;
    @NotNull
    private int billDetailID;
    @NotBlank
    private String itemDescription;
    private MultipartFile itemPicture;
    private Date storedDateStart;
    private Date storedDateEnd;
    private Boolean cusStoredStatus;
}
