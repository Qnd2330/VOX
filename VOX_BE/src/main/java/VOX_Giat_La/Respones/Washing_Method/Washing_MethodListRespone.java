package VOX_Giat_La.Respones.Washing_Method;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Washing_MethodListRespone {
    List<Washing_MethodRespone> washingMethodResponeList;
    private int totalPages;
}
