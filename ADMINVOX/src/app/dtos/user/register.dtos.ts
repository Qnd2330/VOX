import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber, 
    IsDate
} from 'class-validator';

export class RegisterDTO {
    @IsString()
    userName: string;

    @IsPhoneNumber()
    phoneNumber: string;
    
    @IsString()
    @IsNotEmpty()
    userAddress: string;

    @IsString()
    @IsNotEmpty()
    userPassword: string;

    @IsString()
    @IsNotEmpty()
    retypePassword: string;

    @IsDate()
    userBirthDate: Date;

    userGender: string;

    roleID: number = 1;
    constructor(data: any) {
        this.userName = data.userName;
        this.phoneNumber = data.phoneNumber;
        this.userAddress = data.userAddress;
        this.userPassword = data.userPassword;
        this.retypePassword = data.retypePassword;
        this.userBirthDate = data.userBirthDate;
        this.userGender = data.userGender 
        this.roleID = data.roleID || 1;
    }
}