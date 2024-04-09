package VOX_Giat_La.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailsDTO {
    @NotNull(message = "Phải có Id Bill")
    private int billID;
    @NotNull(message = "Phải có Id quần áo")
    private int clothesID;
    @NotNull(message = "Phải có Id kiểu giặt")
    private int washID;
    @Min(0)
    private float weight;
    @Min(0)
    private float price;

}
