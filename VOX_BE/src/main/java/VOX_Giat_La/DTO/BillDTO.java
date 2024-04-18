package VOX_Giat_La.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    @JsonProperty("userID")
    @NotNull(message = "Phải có Id người dùng")
    private int userID;
    @Size(min = 10, message = "Phần giới thiệu ít nhất phải có 10 chữ cái")
    @Size(max = 2000, message = "Phần giới thiệu tối đa là 2000 chữ cái ")
    private String billDescription;
    @Min(0)
    private float sumWeight;
    @Min(0)
    private float cost;

    private Boolean billStatus;

    private MultipartFile image;

    private Date billPayDate;

}
