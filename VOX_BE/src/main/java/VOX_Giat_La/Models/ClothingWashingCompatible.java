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
@Builder
public class ClothingWashingCompatible {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "clothID")
    private KindOfClothing cloth;
    @OneToOne
    @JoinColumn(name = "washID")
    private Washing_Method wash;
    @Column(name = "compatibility")
    private Boolean compatibility;
}
