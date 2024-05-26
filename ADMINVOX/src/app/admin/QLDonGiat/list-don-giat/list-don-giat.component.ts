import { Component, OnInit } from '@angular/core';
import { Bill } from '../../../models/storage';
import { BillService } from '../../../service/bill.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../../../models/user';

@Component({
  selector: 'app-list-don-giat',
  templateUrl: './list-don-giat.component.html',
  styleUrl: './list-don-giat.component.css'
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
        this.bills = apiResponse.data;
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
          this.getBill(this.currentPage, this.itemsPerPage);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error?.error?.message ?? '');
        }
      });
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
}
