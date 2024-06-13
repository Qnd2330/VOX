package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Bill")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billID;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
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
    private Date billPayDate;
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<BillDetails> billDetails;

    @PrePersist
    protected void onCreate() {
        billCreateDate = LocalDateTime.now();
    }

    public void updateCost() {
        this.cost = billDetails.stream()
                .map(BillDetails::getPrice)
                .reduce(0.0f, Float::sum);
    }
    public void updateSumWeight() {
        this.sumWeight = billDetails.stream()
                .map(BillDetails::getWeight)
                .reduce(0.0f, Float::sum);
    }

    public void addBillDetails(BillDetails details) {
        billDetails.add(details);
        details.setBill(this);
        updateCost();
        updateSumWeight();
    }

    public void removeBillDetails(BillDetails details) {
        billDetails.remove(details);
        details.setBill(null);
        updateCost();
        updateSumWeight();
    }
}
