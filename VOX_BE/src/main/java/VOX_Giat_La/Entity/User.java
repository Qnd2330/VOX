package VOX_Giat_La.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private int userID;
    private String userName;
    private String userPassword;
    private String userGender;
    private Date userBirthDate;

    private Role role;


}
