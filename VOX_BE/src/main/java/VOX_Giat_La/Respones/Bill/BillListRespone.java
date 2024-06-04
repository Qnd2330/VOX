package VOX_Giat_La.Respones.Bill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BillListRespone {
    private List<BillRespones> bills;
    private int totalPages;
}
