package VOX_Giat_La.Entity.DaXong;

import jakarta.persistence.*;

@Entity
@Table(name = "Functions")
public class Functions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int FunID;
    @Column
    private int roleID;

    @Column
    private String FunctionsName;
    @Column
    private  Boolean FunctionsAvailability;

    public Functions(int funID, int roleID, String functionsName, Boolean functionsAvailability) {
        FunID = funID;
        this.roleID = roleID;
        FunctionsName = functionsName;
        FunctionsAvailability = functionsAvailability;
    }

    public int getFunID() {
        return FunID;
    }

    public void setFunID(int funID) {
        FunID = funID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getFunctionsName() {
        return FunctionsName;
    }

    public void setFunctionsName(String functionsName) {
        FunctionsName = functionsName;
    }

    public Boolean getFunctionsAvailability() {
        return FunctionsAvailability;
    }

    public void setFunctionsAvailability(Boolean functionsAvailability) {
        FunctionsAvailability = functionsAvailability;
    }
}
