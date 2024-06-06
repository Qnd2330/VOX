import { Bill } from "./bill";
import { Wash } from "./wash_method";

export interface BillDetail {
    billID: number;
    bill: Bill;
    billDetailID:number;
    washName: string;
    description:string;
    weight:number;
    price:number;
    billDetailStatus:boolean;
}