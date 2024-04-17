package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Washing_Method")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Washing_Method {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int washID;
    @Column(name = "washName")
    private String washName;
    @Column(name = "washCost")
    private float washCost;
    @Column(name = "washAvailability")
    private Boolean washAvailability;
    @Column(name = "washDescription")
    private String washDescription;
    @Column(name = "washCreateDate")
    private Date washCreateDate;
}
