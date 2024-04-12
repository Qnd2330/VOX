package VOX_Giat_La.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @NotBlank(message = "Bạn chưa điền số điện thoại")
    private String phoneNumber;
    @NotBlank(message = "Bạn chưa điền mật khẩu")
    private String userPassword;
}
