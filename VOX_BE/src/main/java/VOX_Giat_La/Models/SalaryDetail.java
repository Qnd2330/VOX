package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "SalaryDetail")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salaryDetailID;
    @Column(name = "salaryDetailName")
    private String salaryDetailName;
    @Column(name = "salaryCountType")
    private String salaryCountType;
    @Column(name = "salaryValue")
    private float salaryValue;
}
