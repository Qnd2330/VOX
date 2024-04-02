package VOX_Giat_La.Entity.DaXong;

import jakarta.persistence.*;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Table(name = "Roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;
    @Column
    private String roleName;


    public Roles(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;

    }


    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
