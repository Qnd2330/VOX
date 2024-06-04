package VOX_Giat_La.Respones.Storage;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageListRespone {
    List<StorageRespone> storageRespone;
    private int totalPages;
}
