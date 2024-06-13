import { Component, OnInit } from '@angular/core';
import { Wash } from '../../../models/wash_method';
import { CurrencyPipe } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

import { Bill } from '../../../models/bill';
import { BillService } from '../../../service/bill.service';
import { Router } from '@angular/router';
import { WashingmethodService } from '../../../service/washingmethod.service';

@Component({
  selector: 'app-list-giat-la',
  templateUrl: './list-giat-la.component.html',
  styleUrl: './list-giat-la.component.css',
  providers: [CurrencyPipe]
})
export class ListGiatLaComponent implements OnInit {
  washes: Wash[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";
  constructor(
    private washService: WashingmethodService,
    private router: Router,
    private currencyPipe: CurrencyPipe,
  ) { }

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentBillAdminPage')) || 0;
    }
    this.getWashes(this.currentPage, this.itemsPerPage);
  }
  
  getWashes(page: number, limit: number) {
    this.washService.getAllWashes(page, limit).subscribe({
      next: (apiResponse: any) => {
        this.washes = apiResponse.washingMethodResponeList;
        this.totalPages = apiResponse.totalPages;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching washes:', error.message);
      }
    });
    console.log(this.washes);
  }

  insertWash(){
    this.router.navigate(['/admin/qlgl/insert']);
  }

  updateWash(wash: Wash) {
    debugger      
    this.router.navigate(['/admin/qlgl/update', wash.washID]);
  } 

  deleteWash(wash: Wash) {
    const confirmation = window.confirm('Are you sure you want to delete this user?');
    if (confirmation) {
      this.washService.deleteWash(wash.washID).subscribe({
        next: () => {
          console.log('Bill deleted successfully.');
          this.getWashes(this.currentPage, this.itemsPerPage);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error?.error?.message ?? '');
          this.getWashes(this.currentPage, this.itemsPerPage);
        }
      });
      this.getWashes(this.currentPage, this.itemsPerPage);
    }
  }

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentBillAdminPage', String(this.currentPage));
    this.getWashes(this.currentPage, this.itemsPerPage);
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

  formatCost(washCost: number): string {
    return this.currencyPipe.transform(washCost, 'VND', 'symbol', '1.0-0') || 'N/A';;
  }
}
