package VOX_Giat_La.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClothingWashingCompatibleDTO {
    @NotEmpty(message = "Phải có id kiểu quần áo")
    private int clothID;
    @NotEmpty(message = "Phải có id kiểu giặt")
    private int washID;
    private Boolean compatibility;
}
