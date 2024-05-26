export interface Bill {
    billID:number;
    userID:number;
    billDescription:string;
    sumWeight:number;
    cost:number;
    billCreateDate:Date;
    billStatus:boolean;
    billPayDate:Date;
    image:string;
}