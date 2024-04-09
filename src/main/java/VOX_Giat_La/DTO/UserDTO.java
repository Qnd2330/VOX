package VOX_Giat_La.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Bạn chưa điền tên")
    private String userName;
    @NotNull(message = "Phải có role ID")
    private int roleID;
    @NotBlank(message = "Bạn chưa điền mật khẩu")
    private String userPassword;
    @NotBlank(message = "Bạn chưa điền mật khẩu xác thực")
    private String retypePassword;
    @NotBlank(message = "Bạn chưa điền số điện thoại")
    private String phoneNumber;
    @NotBlank(message = "Bạn chưa điền giới tính")
    private String userGender;
    @NotBlank(message = "Bạn chưa điền địa chỉ")
    private String userAddress;
    private Date userBirthDate;

}
