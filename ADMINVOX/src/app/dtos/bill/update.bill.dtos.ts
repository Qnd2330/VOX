import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class UpdateBillDTO {    
    @IsString()
    @IsNotEmpty()
    billDescription:string;

    
    @IsNotEmpty()
    sumWeight:number;

    
    @IsNotEmpty()
    cost:number;

    
    @IsNotEmpty()
    billCreateDate:Date;

    
    @IsNotEmpty()
    billStatus:boolean;

    
    @IsNotEmpty()
    billPayDate:Date;

    @IsString()
    @IsNotEmpty()
    image:string;
            
    constructor(data: any) {
        this.billDescription = data.billDescription; 
        this.sumWeight = data.sumWeight;
        this.cost = data.cost;
        this.billCreateDate = data.billCreateDate;
        this.billStatus = data.billStatus;
        this.billPayDate = data.billPayDate;
        this.image = data.image;   
    }
}