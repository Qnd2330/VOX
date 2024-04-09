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
public class Washing_MethodDTO {
    @NotNull
    private String washName;
    @Min(0)
    private float washCost;
    @Size(min = 10, message = "Phần giới thiệu ít nhất phải có 10 chữ cái")
    @Size(max = 2000, message = "Phần giới thiệu tối đa là 2000 chữ cái ")
    private String washDescription;
    private String washExpPictures;
}
