import { Component, OnInit } from '@angular/core';
import { customerstorage } from '../../../models/customerstorage';
import { CustomerstorageService } from '../../../service/customerstorage.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { BillDetail } from '../../../models/billdetail';

@Component({
  selector: 'app-list-kho-kh',
  templateUrl: './list-kho-kh.component.html',
  styleUrl: './list-kho-kh.component.css'
})
export class ListKhoKHComponent implements OnInit {
  customerstorage: customerstorage[] = [];
  billDetail: BillDetail[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";
  localStorage?:Storage;

  constructor(
    private customerService: CustomerstorageService,
    private router: Router,
  ) { }

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentBillAdminPage')) || 0;
    }
    this.getCustomerStorage(this.currentPage, this.itemsPerPage);
  }
  
  getCustomerStorage(page: number, limit: number) {
    debugger
    this.customerService.getListCustomerStorage(page, limit).subscribe({
      next: (apiResponse: any) => {
        this.customerstorage = apiResponse.customerStorageRespones;
        this.totalPages = apiResponse.totalPages;
        // this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error?.error?.message ?? '');
      }
    });
    console.log(this.customerstorage)
  }

  insertCusStorage(){
    this.router.navigate(['/admin/qlkkh/them']);
  }

  updateCusStorage(customerStorage: customerstorage) {
    debugger      
    this.router.navigate(['/admin/qlkkh/update', customerStorage.storageID]);
  } 

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentBillAdminPage', String(this.currentPage));
    this.getCustomerStorage(this.currentPage, this.itemsPerPage);
  }

  generateVisiblePageArray(currentPage: number, totalPages: number): number[] {
    const maxVisiblePages = 5;
    const halfVisiblePages = Math.floor(maxVisiblePages / 2);

    let startPage = Math.max(currentPage - halfVisiblePages, 1);
    let endPage = Math.min(startPage + maxVisiblePages - 1, totalPages);

    if (endPage - startPage + 1 < maxVisiblePages) {
      startPage = Math.max(endPage - maxVisiblePages + 1, 1);
    }

    return new Array(endPage - startPage + 1).fill(0)
      .map((_, index) => startPage + index);
  }

  // getDetailBill(bill: Bill) {
  //   debugger
  //   this.router.navigate(['/admin/qldg/view', bill.billID]);
  // }

  // insertBill(){
  //   this.router.navigate(['/admin/qldg/them']);
  // }
}