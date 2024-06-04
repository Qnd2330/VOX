package VOX_Giat_La.Respones.Washing_Method;

import VOX_Giat_La.Models.Washing_Method;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Washing_MethodRespone {
    private int washID;
    private String washName;
    private float washCost;
    private Boolean washAvailability;
    private String washDescription;
    private Date washCreateDate;

    public static Washing_MethodRespone fromWashing_MethodRespone(Washing_Method washingMethod) {
        Washing_MethodRespone washingMethodRespone = Washing_MethodRespone.builder()
                .washID(washingMethod.getWashID())
                .washName(washingMethod.getWashName())
                .washAvailability(washingMethod.getWashAvailability())
                .washDescription(washingMethod.getWashDescription())
                .washCreateDate(washingMethod.getWashCreateDate())
                .build();
        return washingMethodRespone;
    }
}
