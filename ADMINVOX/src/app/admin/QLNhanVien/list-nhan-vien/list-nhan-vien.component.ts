import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../service/employee.service';

@Component({
  selector: 'app-list-nhan-vien',
  templateUrl: './list-nhan-vien.component.html',
  styleUrl: './list-nhan-vien.component.css'
})
export class ListNhanVienComponent implements OnInit{
  users: User[] = [];
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
  ) { }

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentProductAdminPage')) || 0;
    }
    this.getUserByRole('ROLE_EMPLOYEE', this.currentPage, this.itemsPerPage);
  }

  getUserByRole(roleID: string, page: number, limit: number) {
    this.employeeService.getUsersByRole(roleID, page, limit).subscribe({
      next: (apiResponse: any) => {
        this.users = apiResponse.data;
        this.totalPages = apiResponse.totalPages;
      },
      error: (error: HttpErrorResponse) => {
        console.error(error?.error?.message ?? '');
      }
    });
  }

  deleteUser(user: User) {
    const confirmation = window.confirm('Are you sure you want to delete this user?');
    if (confirmation) {
      this.employeeService.deleteUser(user.userID).subscribe({
        next: () => {
          this.getUserByRole(this.keyword, this.currentPage, this.itemsPerPage);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error?.error?.message ?? '');
        }
      });
    }
  }

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentProductAdminPage', String(this.currentPage));
    this.getUserByRole(this.keyword, this.currentPage, this.itemsPerPage);
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

