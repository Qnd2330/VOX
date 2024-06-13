import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // Sửa import từ 'express' sang '@angular/router'
import { UserService } from '../../service/user.service';
import { RegisterDTO } from '../../dtos/user/register.dtos';
import { ApiResponse } from '../../responses/api.response';
import { User } from '../../models/user';
// Thêm import ApiResponse từ models

@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css'] // Sửa thành styleUrls để khai báo CSS
})
export class RegisterationComponent implements OnInit {
  userPasswordModel: string = ''; // Bind to ngModel of userPassword input
  retypePasswordModel: string = ''; // Bind to ngModel of retypePassword input
  addUser: User = {
    userID: 1,
    userName: '',
    userBirthDate: new Date,
    phoneNumber: '',
    userAddress: '',
    userGender: '',
    userPassword: '',
    retypePassword:'',
    roleID: '1',
  };
  isAccepted: boolean;
  constructor(    
    private route: ActivatedRoute,
    private router: Router,   
    private userService: UserService,    
  ) {
    this.isAccepted = false;
  }
  ngOnInit() {
    console.log(this.userPasswordModel, this.retypePasswordModel)
  } 
  insertUser() {    
    const message = `isAccepted: ${this.isAccepted}`;
                    
    debugger;
    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.userService.insertUser(this.addUser).subscribe({
      next: () => {
        debugger;
        this.router.navigate(['/login']);        
      },
      error: (error) => {
        debugger;
        console.error(error);
        this.router.navigate(['/login']); 
        // alert(error);
      }        
    });    
  }
}
