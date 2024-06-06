import { BillDetail } from "../../models/billdetail";
import { User } from "../../models/user";


export interface BillRespones {
    billID:number;
    userID:number;
    userName:string;
    billDescription:string;
    sumWeight:number;
    cost:number;
    billCreateDate:Date;
    billStatus:boolean;
    billPayDate:Date;
    image:string;
    billDetail: BillDetail[];
}