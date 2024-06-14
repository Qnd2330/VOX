import { Component, OnInit } from '@angular/core';
import { customerstorage } from '../../../models/customerstorage';
import { InsertCusStorageDTO } from '../../../dtos/cusstorage/insert.cusstorage.dtos';
import { BillDetail } from '../../../models/billdetail';
import { storages } from '../../../models/storage';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerstorageService } from '../../../service/customerstorage.service';
import { HttpErrorResponse } from '@angular/common/http';
import { StorageService } from '../../../service/storage.service';
import { BilldetailsService } from '../../../service/billdetails.service';

@Component({
  selector: 'app-them-kho-kh',
  templateUrl: './them-kho-kh.component.html',
  styleUrl: './them-kho-kh.component.css'
})
export class ThemKhoKHComponent implements OnInit {
  currentPage: number = 0;
  itemsPerPage: number = 12;
  cusItemID: number = 0;
  insertCusStorage: InsertCusStorageDTO = {
    storageID: 0,
    billDetailID: 1,
    itemDescription: '',
    storedDateStart: new Date,
    storedDateEnd: new Date,
    cusStoredStatus: false,
  };
  cusStorage: customerstorage[] = [];
  storage: storages[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private cusStorageService: CustomerstorageService,
    private storageService: StorageService,
  ) {
  }
  ngOnInit() {
    debugger
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentBillAdminPage')) || 0;
    }
    this.getPosition(this.currentPage, this.itemsPerPage); // Gọi hàm loadWashes khi component được khởi tạo
  }
  addCusStorage(): void {
    debugger;
    this.cusItemID = Number(this.route.snapshot.paramMap.get('id'));
    debugger;

    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.cusStorageService.insertCusStorage(this.insertCusStorage).subscribe({
      next: () => {
        debugger;
        this.router.navigate([`/admin/qlkkh`]);
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error);
        this.router.navigate([`/admin/qlkkh`]);
        // alert(error);
      }
    });
  }

  getPosition(page: number, limit: number) {
    debugger
    this.storageService.getAllStorage(page, limit).subscribe({
      next: (apiResponse: any) => {
        debugger;
        this.storage = apiResponse.storageRespone;
      },
      complete: () => {
        debugger;
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
      }
    });
    console.log(this.storage)
  }
}