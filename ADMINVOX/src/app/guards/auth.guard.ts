import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../guards/auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) { }

    canActivate(): boolean {
        if (this.authService.isLoggedIn()) {
            return true;
        } else {
            this.router.navigate(['/login']);
            return false;
        }
    }
}

// import { Injectable } from '@angular/core';
// import {
//   ActivatedRouteSnapshot,
//   RouterStateSnapshot,
//   CanActivateFn
// } from '@angular/router';

// import { Router } from '@angular/router'; // Đảm bảo bạn đã import Router ở đây.
// import { inject } from '@angular/core';
// import { TokenService } from '../service/token.service';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthGuard {
//   constructor(
//     private tokenService: TokenService,
//     private router: Router,
//   ) {}

//   canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
//     const isTokenExpired = this.tokenService.isTokenExpired();
//     const isUserIdValid = this.tokenService.getUserId() > 0;
//     debugger
//     if (!isTokenExpired && isUserIdValid) {
//       return true;
//     } else {
//       // Nếu không authenticated, bạn có thể redirect hoặc trả về một UrlTree khác.
//       // Ví dụ trả về trang login:
//       this.router.navigate(['/login']);
//       return false;
//     }
//   }
// }

// // Sử dụng functional guard như sau:
// export const AuthGuardFn: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean => {
//   debugger
//   return inject(AuthGuard).canActivate(next, state);
// }
