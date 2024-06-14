import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { UserService } from '../../../service/user.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-ttkh',
  templateUrl: './list-ttkh.component.html',
  styleUrl: './list-ttkh.component.css'
})
export class ListTTKHComponent implements OnInit {
  users: User[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";

  constructor(
    private userService: UserService,
    private router: Router,
  ) { }   

  ngOnInit() {
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentUserAdminPage')) || 0;
    }
    this.getUser(this.keyword, this.currentPage, this.itemsPerPage);
  }

  getUser(keyword: string, page: number, limit: number) {
    debugger
    this.userService.getUsers(keyword, page, limit).subscribe({
        next: (apiResponse: any) => {
            console.log('API Response:', apiResponse);
            // Filter users by roleID
            this.users = apiResponse.userResponeList.filter((user: any) => user.roleID === 'ROLE_USER');
            console.log('Filtered Users:', this.users);
            this.totalPages = apiResponse.totalPages;
            console.log('TotalPage:', this.totalPages);
            // this.visiblePages = this.generateVisiblePageArray(this.currentPage, this.totalPages);
        },
        error: (error: HttpErrorResponse) => {
            console.error(error?.error?.message ?? '');
        }
    });
}

  insertUser(){
    this.router.navigate(['/admin/qltk/insert']);
  }

  updateUser(user: User) {
    debugger      
    this.router.navigate(['/admin/qltk/update', user.userID]);
  } 

  deleteUser(user: User) {
    const confirmation = window.confirm('Are you sure you want to delete this user?');
    if (confirmation) {
      this.userService.deleteUser(user.userID).subscribe({
        next: (apiResponse: any) => {
          console.log('Xóa thành công:', apiResponse);
        },
        complete: () => {
          console.log('Hoàn thành thao tác xóa, tải lại trang...');
          location.reload();         
        },
        error: (error: HttpErrorResponse) => {
          console.error('Đã xảy ra lỗi khi xóa người dùng:',error?.error?.message ?? '');
        }
      });
    }
    else {
      console.log('Hủy xóa người dùng');
    }
  }

  onPageChange(page: number) {
    this.currentPage = page < 0 ? 0 : page;
    localStorage.setItem('currentUserAdminPage', String(this.currentPage));
    this.getUser(this.keyword, this.currentPage, this.itemsPerPage);
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

  getRoleName(roleID: string): string {
    switch (roleID) {
      case 'ROLE_ADMIN':
        return 'Quản trị viên';
      case 'ROLE_USER':
        return 'Khách Hàng';
      case 'ROLE_EMPLOYEE':
        return 'Nhân viên';
      default:
        return 'Không xác định';
    }
  }
}
{

}
