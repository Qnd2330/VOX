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
public class SalaryDTO {
    @NotNull
    private int userID;
    @NotNull
    private int salaryDetailID;
    @NotNull
    private int scheduleID;
    @Min(0)
    private float salaryPerDay;
    @Min(0)
    private float salaryTotal;
}
