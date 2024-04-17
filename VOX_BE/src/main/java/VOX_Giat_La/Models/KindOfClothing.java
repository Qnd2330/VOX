package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KindOfClothing")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KindOfClothing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clothesID;
    @Column(name = "clothesType")
    private String clothesType;
    @Column(name = "clothesAvailability")
    private Boolean clothesAvailability;
}
