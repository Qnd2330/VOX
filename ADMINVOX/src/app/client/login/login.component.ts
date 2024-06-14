import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../service/user.service';
import { LoginDTO } from '../../dtos/user/login.dtos';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Role } from '../../models/role';
import { UserResponse } from '../../responses/user/user.response';
// import { TokenService } from '../../service/token.service';
import { ApiResponse } from '../../responses/api.response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('loginForm') loginForm!: NgForm;

  /*
  //Login user1
  phoneNumber: string = '33445566';
  password: string = '123456789';

  //Login user2
  phoneNumber: string = '0964896239';
  password: string = '123456789';


  //Login admin
  phoneNumber: string = '11223344';
  password: string = '11223344';

  */
  phoneNumber: string = '0969858603';
  userPassword: string = 'ngocviet@123';
  showPassword: boolean = false;

  roles: Role[] = []; // Mảng roles
  rememberMe: boolean = true;
  selectedRole: Role | undefined; // Biến để lưu giá trị được chọn từ dropdown
  userResponse?: UserResponse

  onPhoneNumberChange() {
    console.log(`Phone typed: ${this.phoneNumber}`);
    //how to validate ? phone must be at least 6 characters
  }
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    // private tokenService: TokenService,
  ) { }

  ngOnInit() {
    // Gọi API lấy danh sách roles và lưu vào biến roles
    debugger
  }
  createAccount() {
    debugger
    // Chuyển hướng người dùng đến trang đăng ký (hoặc trang tạo tài khoản)
    this.router.navigate(['/register']); 
  }
  login() {
    const loginDTO: LoginDTO = {
      phoneNumber: this.phoneNumber,
      userPassword: this.userPassword,
      roleID: this.selectedRole?.roleID ?? 1
    };

    this.userService.login(loginDTO).subscribe({
      next: (response: any) => {
        // Xử lý phản hồi khi đăng nhập thành công
        console.log('Login success:', response);

        // Chuyển hướng dựa trên roleID
        switch (loginDTO.roleID) {
          case 1:
            this.router.navigate(['/admin']);
            break;
          case 2:
            this.router.navigate(['/home']);
            break;
          default:
            this.router.navigate(['/default']);
            break;
        }
      },
      error: (error: HttpErrorResponse) => {
        // Xử lý lỗi đăng nhập
        console.error('Login error:', error.error.message ?? 'Unknown error');
        // Thực hiện các hành động cần thiết như hiển thị thông báo lỗi, reset form, ...
      }
    });
  }
  togglePassword() {
    this.showPassword = !this.showPassword;
  }
}
