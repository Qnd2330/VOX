import {
    IsString, 
    IsNotEmpty, 
    IsPhoneNumber, 
    IsDate
} from 'class-validator';

export class LoginDTO {
    @IsPhoneNumber()
    phoneNumber: string;

    @IsString()
    @IsNotEmpty()
    userPassword: string;

    roleID: number;

    constructor(data: any) {
        this.phoneNumber = data.phone_number;
        this.userPassword = data.userPassword;
        this.roleID = data.roleID
    }
}