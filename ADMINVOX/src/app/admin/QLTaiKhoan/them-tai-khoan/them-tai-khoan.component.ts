import { Component, OnInit } from '@angular/core';
import { User } from '../../../models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-them-tai-khoan',
  templateUrl: './them-tai-khoan.component.html',
  styleUrl: './them-tai-khoan.component.css'
})
export class ThemTaiKhoanComponent implements OnInit{
  userPasswordModel: string = ''; // Bind to ngModel of userPassword input
  retypePasswordModel: string = ''; // Bind to ngModel of retypePassword input
  addUser: User = {
    userID: 1,
    userName: '',
    userBirthDate: new Date,
    phoneNumber: '',
    userAddress: "Hãy viết những ghi chú",
    userGender: '',
    userPassword: '',
    retypePassword:'',
    roleID: '',
  };
  constructor(    
    private route: ActivatedRoute,
    private router: Router,   
    private userService: UserService,    
  ) {
  }
  ngOnInit() {
    console.log(this.userPasswordModel, this.retypePasswordModel)
  } 
  insertUser() {    
    debugger;
  
    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.userService.insertUser(this.addUser).subscribe({
      next: () => {
        debugger;
        this.router.navigate(['/admin/qltk']);        
      },
      error: (error) => {
        debugger;
        console.error(error);
        this.router.navigate(['/admin/qltk']); 
        // alert(error);
      }        
    });    
  }
}
