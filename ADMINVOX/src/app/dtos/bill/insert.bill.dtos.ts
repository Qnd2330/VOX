import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class InsertBillDTO { 
    @IsNotEmpty()
    userID: number;
    
    @IsString()
    @IsNotEmpty()
    billDescription:string;

    
    @IsNotEmpty()
    sumWeight:number;

    
    @IsNotEmpty()
    cost:number;
    
    @IsNotEmpty()
    billStatus:boolean;

    
    @IsNotEmpty()
    billPayDate:Date;

    @IsString()
    @IsNotEmpty()
    image:string;
            
    constructor(data: any) {
        this.userID = data.userID; 
        this.billDescription = data.billDescription; 
        this.sumWeight = data.sumWeight;
        this.cost = data.cost;
        this.billStatus = data.billStatus;
        this.billPayDate = data.billPayDate;
        this.image = data.image;   
    }
}