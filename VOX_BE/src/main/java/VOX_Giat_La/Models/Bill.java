package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Bill")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User userID;
    @Column(name = "billDescription")
    private String billDescription;
    @Column(name = "sumWeight")
    private float sumWeight;
    @Column(name = "cost")
    private float cost;
    @Column(name = "billCreateDate")
    private LocalDateTime billCreateDate;
    @Column(name = "billStatus")
    private Boolean billStatus;
    @Column(name = "billPayDate")
    private LocalDateTime billPayDate;
    @Column(name = "image")
    private MultipartFile image;

    @PrePersist
    protected void onCreate() {
        billCreateDate = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        billPayDate = LocalDateTime.now();
    }
}
