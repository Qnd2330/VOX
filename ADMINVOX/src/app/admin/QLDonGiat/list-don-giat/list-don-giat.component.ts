import { Component, OnInit } from '@angular/core';
import { BillService } from '../../../service/bill.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../../../models/user';
import { Bill } from '../../../models/bill';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-list-don-giat',
  templateUrl: './list-don-giat.component.html',
  styleUrl: './list-don-giat.component.css',
  providers: [CurrencyPipe]
})
export class ListDonGiatComponent implements OnInit {
  bills: Bill[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";

  constructor(
    private billService: BillService,
    private router: Router,
    private currencyPipe: CurrencyPipe,
  ) { }

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentBillAdminPage')) || 0;
    }
    this.getBill(this.currentPage, this.itemsPerPage);
  }
  
  getBill(page: number, limit: number) {
    this.billService.getBill(page, limit).subscribe({
      next: (apiResponse: any) => {
        this.bills = apiResponse.bills;
        this.totalPages = apiResponse.totalPages;
        // this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error?.error?.message ?? '');
      }
    });
  }

  updateBill(billID: number) {
    debugger      
    this.router.navigate(['/admin/bill/update', billID]);
  } 

  deleteBill(bill: Bill) {
    const confirmation = window.confirm('Are you sure you want to delete this user?');
    if (confirmation) {
      this.billService.deleteBill(bill.billID).subscribe({
        next: () => {
          console.log('Bill deleted successfully.');
          this.getBill(this.currentPage, this.itemsPerPage);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error?.error?.message ?? '');
          this.getBill(this.currentPage, this.itemsPerPage);
        }
      });
      this.getBill(this.currentPage, this.itemsPerPage);
    }
  }

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentBillAdminPage', String(this.currentPage));
    this.getBill(this.currentPage, this.itemsPerPage);
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

  getDetailBill(bill: Bill) {
    debugger
    this.router.navigate(['/admin/qldg/view', bill.billID]);
  }

  insertBill(){
    this.router.navigate(['/admin/qldg/them']);
  }

  formatCost(cost: number): string {
    return this.currencyPipe.transform(cost, 'VND', 'symbol', '1.0-0') || 'N/A';;
  }
}
