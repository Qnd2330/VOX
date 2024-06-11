package VOX_Giat_La.DTO;

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
public class BillDetailsDTO {
    @NotNull(message = "Phải có Id Bill")
    private int billID;
    @NotNull(message = "Phải có Id kiểu giặt")
    private int washID;
    @Size(min = 10, message = "Phần giới thiệu ít nhất phải có 10 chữ cái")
    @Size(max = 2000, message = "Phần giới thiệu tối đa là 2000 chữ cái ")
    private String description;
    @Min(0)
    private float weight;
    @Min(0)
    private float price;
    private boolean billDetailStatus;

}
