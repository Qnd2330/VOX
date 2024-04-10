package VOX_Giat_La.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KindOfClothingDTO {
    @NotBlank
    private String clothesType;
    private Boolean clothesAvailability;
}
