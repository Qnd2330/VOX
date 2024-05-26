package VOX_Giat_La.Respones;


import VOX_Giat_La.Models.User;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespone {
    private int userID;
    private String userName;
    private String roleID;
    private String userGender;

    public static UserRespone fromUser(User user){
        UserRespone userRespone = UserRespone.builder()
                .userID(user.getUserID())
                .userName(user.getUsername())
                .roleID(user.getRoleName(user.getRole().getRoleID()))
                .userGender(user.getUserGender())
                .build();
        return  userRespone;
    }
}

