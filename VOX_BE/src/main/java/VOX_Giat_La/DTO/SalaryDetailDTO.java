package VOX_Giat_La.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDetailDTO {
    @NotBlank
    private String salaryDetailName;
    @NotBlank
    private String salaryCountType;
    @Min(0)
    private float salaryValue;
}
