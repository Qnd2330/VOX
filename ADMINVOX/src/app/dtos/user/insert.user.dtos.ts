import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber,     
} from 'class-validator';

export class InsertUserDTO {    
    @IsString()
    @IsNotEmpty()
    userName: string;

    @IsString()
    @IsNotEmpty()
    roleID: string;

    @IsString()
    @IsNotEmpty()
    userPassword: string;

    @IsNotEmpty()
    @IsPhoneNumber()
    phoneNumber: number;

    @IsString()
    @IsNotEmpty()
    userGender: string;

    @IsString()
    @IsNotEmpty()
    userAddress: string;

    @IsString()
    @IsNotEmpty()
    userBirthDate: Date;
            
    constructor(data: any) {
        this.userName = data.userName; 
        this.roleID = data.roleID;
        this.userPassword = data.userPassword;
        this.phoneNumber = data.phoneNumber;
        this.userGender = data.userGender;
        this.userAddress = data.userAddress;
        this.userBirthDate = data.userBirthDate;   
    }
}