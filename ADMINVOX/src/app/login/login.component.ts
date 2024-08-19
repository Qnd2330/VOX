import { Component } from '@angular/core';
import { AuthService } from '../guards/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  phoneNumber: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit() {
    this.authService.login(this.phoneNumber, this.password).subscribe(
      response => {
        if (response && response.token) {
          // Lưu token và điều hướng tới trang admin
          this.authService.setToken(response.token);
          this.router.navigate(['/admin']);
        } else {
          console.log('Đăng nhập thất bại:', response);
        }
      },
      error => {
        console.error('Đã xảy ra lỗi:', error);
      }
    );
  }
}
