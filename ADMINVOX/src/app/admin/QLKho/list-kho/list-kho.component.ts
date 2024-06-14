import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from '../../../service/storage.service';
import { HttpErrorResponse } from '@angular/common/http';
import { storages } from '../../../models/storage';

@Component({
  selector: 'app-list-kho',
  templateUrl: './list-kho.component.html',
  styleUrl: './list-kho.component.css'
})
export class ListKhoComponent implements OnInit {
  storage: storages[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";
  localStorage?:Storage;

  constructor(
    private storageService: StorageService,
    private router: Router,
  ) { }

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentBillAdminPage')) || 0;
    }
    this.getStorage(this.currentPage, this.itemsPerPage);
  }
  
  getStorage(page: number, limit: number) {
    debugger
    this.storageService.getAllStorage(page, limit).subscribe({
      next: (apiResponse: any) => {
        this.storage = apiResponse.storageRespone;
        this.totalPages = apiResponse.totalPages;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching washes:', error.message);
      }
    });
    console.log(this.storage);
  }

  insertStorage(){
    this.router.navigate(['/admin/qlk/insert']);
  }

  updateStorage(storage: storages) {
    debugger      
    this.router.navigate(['/admin/qlk/update', storage.storageID]);
  } 

  deleteStorage(storage: storages) {
    const confirmation = window.confirm('Are you sure you want to delete this user?');
    if (confirmation) {
      this.storageService.deleteStorage(storage.storageID).subscribe({
        next: () => {
          console.log('Bill deleted successfully.');
          this.getStorage(this.currentPage, this.itemsPerPage);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error?.error?.message ?? '');
          this.getStorage(this.currentPage, this.itemsPerPage);
        }
      });
      this.getStorage(this.currentPage, this.itemsPerPage);
    }
  }

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentBillAdminPage', String(this.currentPage));
    this.getStorage(this.currentPage, this.itemsPerPage);
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
