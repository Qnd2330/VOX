package VOX_Giat_La.Respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRespones {
    private int userID;
    private String userName;

    private String billDescription;

    private float sumWeight;
    private Boolean billStatus;

    private float cost;

    private String image;

    private Date billPayDate;

    private LocalDateTime billCreateDate;

}
