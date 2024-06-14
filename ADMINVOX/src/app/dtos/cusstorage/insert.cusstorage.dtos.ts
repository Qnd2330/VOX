import {
    IsString,
    IsNotEmpty,
} from 'class-validator';

export class InsertCusStorageDTO {
    @IsNotEmpty()
    storageID: number;

    @IsNotEmpty()
    billDetailID: number;

    @IsNotEmpty()
    itemDescription: string;

    storedDateStart: Date;

    storedDateEnd: Date;

    @IsNotEmpty()
    cusStoredStatus: boolean;

    constructor(data: any) {
        this.storageID = data.storageID; 
        this.billDetailID = data.billDetailID;
        this.itemDescription = data.itemDescription;
        this.storedDateStart = data.storedDateStart;
        this.storedDateEnd = data.storedDateEnd;
        this.cusStoredStatus = data.cusStoredStatus;
    }
}
