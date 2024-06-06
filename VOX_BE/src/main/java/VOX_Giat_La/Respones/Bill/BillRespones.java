package VOX_Giat_La.Respones.Bill;

import VOX_Giat_La.Models.Bill;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRespones {
    private int billID;
    private int userID;
    private String userName;

    private String billDescription;

    private float sumWeight;


    private float cost;

    private LocalDateTime billCreateDate;



   private Date billPayDate;
    private Boolean billStatus;
    public static BillRespones fromBill(Bill bill) {
        BillRespones billRespones =BillRespones.builder()
                .billID(bill.getBillID())
                .userID(bill.getUser().getUserID())
                .userName(bill.getUser().getUsername())
                .billDescription(bill.getBillDescription())
                .sumWeight(bill.getSumWeight())
                .cost(bill.getCost())
                .billStatus(bill.getBillStatus())
//                .image(bill.getImage())
                .billPayDate(bill.getBillPayDate())
                .build();
        billRespones.setBillCreateDate(bill.getBillCreateDate());
        billRespones.setBillPayDate(bill.getBillPayDate());
        return billRespones;
    }

}
