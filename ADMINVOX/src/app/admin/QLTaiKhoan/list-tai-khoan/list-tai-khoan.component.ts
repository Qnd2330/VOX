import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-tai-khoan',
  templateUrl: './list-tai-khoan.component.html',
  styleUrls: ['./list-tai-khoan.component.css'] // Note the correction here, should be 'styleUrls' instead of 'styleUrl'
})
export class ListTaiKhoanComponent implements OnInit {
  users: User[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";

  constructor(
    private  userService: UserService,
    private router: Router,
  ) { }   

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentUserAdminPage')) || 0;
    }
    this.getUser(this.keyword, this.currentPage, this.itemsPerPage);
  }

  getUser(keyword: string, page: number, limit: number) {
    this.userService.getUsers(keyword, page, limit).subscribe({
      next: (apiResponse: any) => {
        console.log('API Response:', apiResponse);
        this.users = apiResponse.userResponeList;
        console.log('Users:', this.users);
        this.totalPages = apiResponse.totalPages;
        console.log('TotalPage:', this.totalPages);
        // this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error?.error?.message ?? '');
      }
    });
  }

  deleteUser(user: User) {
    const confirmation = window.confirm('Are you sure you want to delete this user?');
    if (confirmation) {
      this.userService.deleteUser(user.userID).subscribe({
        next: () => {
          this.getUser(this.keyword, this.currentPage, this.itemsPerPage);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error?.error?.message ?? '');
        }
      });
    }
  }

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentUserAdminPage', String(this.currentPage));
    this.getUser(this.keyword, this.currentPage, this.itemsPerPage);
  }

  // generateVisiblePageArray(currentPage: number, totalPages: number): number[] {
  //   const maxVisiblePages = 5;
  //   const halfVisiblePages = Math.floor(maxVisiblePages / 2);

  //   let startPage = Math.max(currentPage - halfVisiblePages, 1);
  //   let endPage = Math.min(startPage + maxVisiblePages - 1, totalPages);

  //   if (endPage - startPage + 1 < maxVisiblePages) {
  //     startPage = Math.max(endPage - maxVisiblePages + 1, 1);
  //   }

  //   return new Array(endPage - startPage + 1).fill(0)
  //     .map((_, index) => startPage + index);
  // }
}
