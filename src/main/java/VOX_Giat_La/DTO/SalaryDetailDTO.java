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
public class SalaryDetailDTO {
    @NotNull
    private String salaryDetailName;
    @NotNull
    private String salaryCountType;
    @Min(0)
    private float salaryValue;
}
