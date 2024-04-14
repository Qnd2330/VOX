package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
    @PrePersist
    protected void onCreate() {
        roleCreateDate = LocalDateTime.now();
    }
}
