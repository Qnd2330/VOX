package VOX_Giat_La.Respones.Bill;

import VOX_Giat_La.Models.BillDetails;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailRespone {
    private int billDetailID;
    private int billID;

    private String washName;

    private String description;

    private float weight;

    private float price;

    private Boolean billDetailStatus;

    public static BillDetailRespone fromBillDetail(BillDetails billDetails) {
        BillDetailRespone billDetailRespone =BillDetailRespone.builder()
                .billDetailID(billDetails.getBillDetailID())
                .billID(billDetails.getBill().getBillID())
                .washName(billDetails.getWash().getWashName())
                .description(billDetails.getDescription())
                .weight(billDetails.getWeight())
                .price(billDetails.getPrice())
                .billDetailStatus(billDetails.getBillDetailStatus())
                .build();
        return billDetailRespone;
    }



}
