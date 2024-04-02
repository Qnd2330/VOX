package VOX_Giat_La.Entity.DaXong;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column
    private String userName;

    @OneToOne
    @JoinColumn(name="Roles_roleID")
    private Roles role;
    @Column
    private String userPassword;
    @Column
    private String userGender;
    @Column
    private Date userBirthDate;
    @Column
    private Date userCreateDate;

    public User(int userID, String userName, Roles role, String userPassword, String userGender, Date userBirthDate, Date userCreateDate) {
        this.userID = userID;
        this.userName = userName;
        this.role = role;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userBirthDate = userBirthDate;
        this.userCreateDate = userCreateDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Roles getRoleID() {
        return role;
    }



    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public Date getUserCreateDate() {
        return userCreateDate;
    }

    public void setUserCreateDate(Date userCreateDate) {
        this.userCreateDate = userCreateDate;
    }
}
