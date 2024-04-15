package VOX_Giat_La.Models;

import VOX_Giat_La.DTO.Washing_MethodDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "ClothingWashingCompatible")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClothingWashingCompatible {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "clothID")
    private KindOfClothing clothID;
    @OneToOne
    @JoinColumn(name = "washID")
    private Washing_Method washID;
    @Column(name = "compatibility")
    private Boolean compatibility;
}
