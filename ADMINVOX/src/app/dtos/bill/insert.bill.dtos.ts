import {
    IsString, 
    IsNotEmpty, 
} from 'class-validator';

export class InsertBillDTO { 
    
    @IsNotEmpty()
    userID: number;
    
    @IsString()
    @IsNotEmpty()
    billDescription: string;

    @IsNotEmpty()
    sumWeight: number;

    @IsNotEmpty()
    cost: number;
    
    @IsNotEmpty()
    billStatus:boolean;

    image: string | null;

    @IsNotEmpty()
    billPayDate: Date;

    constructor(data: any) {
        this.userID = data.userID; 
        this.billDescription = data.billDescription; 
        this.sumWeight = data.sumWeight;
        this.cost = data.cost;
        this.billStatus = data.billStatus;
        this.image = data.image;  
        this.billPayDate = data.billPayDate; 
    }
}
