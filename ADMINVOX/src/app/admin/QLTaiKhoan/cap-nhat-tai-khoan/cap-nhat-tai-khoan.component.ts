import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { UserService } from '../../../service/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-cap-nhat-tai-khoan',
  templateUrl: './cap-nhat-tai-khoan.component.html',
  styleUrl: './cap-nhat-tai-khoan.component.css'
})
export class CapNhatTaiKhoanComponent implements OnInit {
  userID :number = 0;
  currentPage: number = 0;
  itemsPerPage: number = 12;
  user: User = {} as User;

  constructor(    
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    ) {

    }
    ngOnInit() {
      this.getUserDetail();
    }

    getUserDetail() {
      debugger
      this.userID = Number(this.route.snapshot.paramMap.get('id'));
      this.userService.getDetailUser(this.userID).subscribe({
        next: (apiResponse: any) => {  
          this.user = apiResponse;
          console.log(this.user)
        },
        complete: () => {
        },
        error: (error: HttpErrorResponse) => {
          debugger;
          console.error(error?.error?.message ?? '');
        } 
      });
    }    

    saveUser(): void {   
      debugger        
      this.userService.updateUser(this.userID, this.user).subscribe({
        next: (apiResponse: any) => {  
          debugger        
        },
        complete: () => {
          debugger;
          this.router.navigate(['/admin/qltk']);        
        },
        error: (error: HttpErrorResponse) => {
          debugger;
          console.error(error?.error?.message ?? '');
        } 
      });  
    }
}