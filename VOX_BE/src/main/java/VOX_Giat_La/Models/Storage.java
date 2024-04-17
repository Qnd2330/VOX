package VOX_Giat_La.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Storage")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storageID;
    @Column(name = "storagePosition")
    private String storagePosition;
    @Column(name = "storageStatus")
    private Boolean storageStatus;
    @Column(name = "storageCreateDate")
    private LocalDateTime storageCreateDate;
    @PrePersist
    protected void onCreate() {
        storageCreateDate = LocalDateTime.now();
    }
}
