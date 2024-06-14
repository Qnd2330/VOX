import { Role } from "../../models/role";

export interface UserResponse {
    userID: number;
    userName: string;
    phoneNumber: string;
    userAddress:string;
    userBirthDate: Date;
    userGender: string;
    role: Role;    
}

