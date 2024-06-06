import { IsString, 
    IsNotEmpty, 
    IsPhoneNumber, 
    IsNumber, ArrayMinSize, 
    ValidateNested, 
    Length 
  } from 'class-validator';
  import { Type } from 'class-transformer';
  
  export class BillDTO {
    billID: number;
  
    userID: number;
  
    userName: string;
  
    billDescription: string;
    
    sumWeight: number;
    
    cost: number;
  
    billCreateDate: Date;
    
    billPayDate: Date;
  
    billStatus: boolean;
  
    image: string;
  
    constructor(data: any) {
      this.billID = data.billID;
      this.userID = data.userID;
      this.userName = data.userName;
      this.billDescription = data.billDescription;
      this.sumWeight = data.sumWeight;
      this.cost = data.cost;
      this.billCreateDate = data.billCreateDate;
      this.billStatus = data.billStatus;
      this.billPayDate = data.billPayDate;
      this.image = data.image;
    }
  }