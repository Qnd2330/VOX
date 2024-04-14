package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "User")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(name = "userName")
    private String userName;
    @OneToOne
    @JoinColumn(name = "roleID")
    private Roles roleID;
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name = "retypePassword")
    private String retypePassword;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "userGender")
    private String userGender;
    @Column(name = "userAddress")
    private String userAddress;
    @Column(name = "userBirthDate")
    private Date userBirthDate;
}
