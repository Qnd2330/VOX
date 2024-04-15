package VOX_Giat_La.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "StoreStorage")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeItemID;
    @OneToOne
    @JoinColumn(name = "storageID")
    private Storage storage;
    @Column(name = "storeItemName")
    private String storeItemName;
    @Column(name = "storeItemDescription")
    private String storeItemDescription;
    @Column(name = "storeItemQuantity")
    private int storeItemQuantity;
    @Column(name = "storeItemCost")
    private float storeItemCost;
    @Column(name = "storeItemPicture")
    private String storeItemPicture;
    @Column(name = "storeItemDayIn")
    private LocalDateTime storeItemDayIn;
    @Column(name = "storeItemStatus")
    private Boolean storeItemStatus;
}
