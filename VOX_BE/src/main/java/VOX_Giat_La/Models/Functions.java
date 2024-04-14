package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Functions")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Functions {
    @ManyToMany
    @JoinColumn(name = "roleID")
    private Roles roleID;
    @Column(name = "functionName")
    private String functionName;
    @Column(name = "functionAvailability")
    private Boolean functionAvailability;
}
