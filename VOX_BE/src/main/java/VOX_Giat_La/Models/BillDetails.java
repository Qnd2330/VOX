package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "BillDetails")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billDetailID;
    @ManyToOne
    @JoinColumn(name = "billID")
    @ToString.Exclude
    private Bill bill;
    @ManyToOne // Sửa lại quan hệ thành ManyToOne
    @JoinColumn(name = "washID")
    private Washing_Method wash;
    @Column(name = "description")
    private String description;
    @Column(name = "weight")
    private float weight;
    @Column(name = "price")
    private float price;
    @Column(name = "billDetailStatus")
    private boolean billDetailStatus;

    @PrePersist
    @PreUpdate
    private void calculatePrice() {
        if (wash != null && weight > 0) {
            this.price = wash.getWashCost() * weight;
        }
    }
    @PostPersist
    @PostUpdate
    private void updateBillCost() {
        if (bill != null) {
            bill.updateCost();
        }
    }
}
