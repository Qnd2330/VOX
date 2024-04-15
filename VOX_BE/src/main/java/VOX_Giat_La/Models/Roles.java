package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Roles")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;
    @Column(name = "roleName")
    private String roleName;
    @Column(name = "roleCreateDate")
    private LocalDateTime roleCreateDate;

    @ManyToMany
    private List<Functions> functions;
    @PrePersist
    protected void onCreate() {
        roleCreateDate = LocalDateTime.now();
    }

}
