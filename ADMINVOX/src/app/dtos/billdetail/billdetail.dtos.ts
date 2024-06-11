import {
    IsString,
    IsNotEmpty,
    IsPhoneNumber,
    IsNumber, ArrayMinSize,
    ValidateNested,
    Length
} from 'class-validator';
import { Type } from 'class-transformer';

export class BillDetailDTO {
    billDetailID: number;

    billID: number;

    washID: number;

    description: string;

    weight: number;

    price: number;

    billDetailStatus: boolean;

    constructor(data: any) {
        this.billDetailID = data.billDetailID;
        this.billID = data.billID;
        this.washID = data.washID;
        this.description = data.description;
        this.weight = data.weight;
        this.price = data.price;
        this.billDetailStatus = data.billDetailStatus;
    }
}