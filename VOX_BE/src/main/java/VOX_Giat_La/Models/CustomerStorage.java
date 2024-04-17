package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "CustomerStorage")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cusItemID;
    @OneToOne
    @JoinColumn(name = "storageID")
    private Storage storage;
    @OneToOne
    @JoinColumn(name = "billDetailID")
    private BillDetails billDetail;
    @Column(name = "itemDescription")
    private String itemDescription;
    @Column(name = "itemPicture")
    private String itemPicture;
    @Column(name = "storedDateStart")
    private LocalDateTime storedDateStart;
    @Column(name = "storedDateEnd")
    private LocalDateTime storedDateEnd;
    @PrePersist
    protected void onCreate() {
        storedDateStart = LocalDateTime.now();
    }
    @PreRemove
    protected void onUpdate() {
        storedDateEnd = LocalDateTime.now();
    }
    @Column(name = "cusStoredStatus")
    private Boolean cusStoredStatus;
}
