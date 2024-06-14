import { BillDetail } from "./billdetail";
import { storages } from "./storage";

export interface customerstorage {
    cusItemID: number;
    storageID: number;
    storagePosition: number;
    storage: storages;
    billDetailID: number;
    billDetail: BillDetail;
    itemDescription: string;
    cusStoredStatus: boolean;
}