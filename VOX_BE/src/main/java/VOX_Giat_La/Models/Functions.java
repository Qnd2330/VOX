package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Functions")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Functions {
    @Id
    private int id;
    @ManyToMany
    @JoinTable(
            name = "functions_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleID"))
    private List<Roles> role;
    @Column(name = "functionName")
    private String functionName;
    @Column(name = "functionAvailability")
    private Boolean functionAvailability;
}
