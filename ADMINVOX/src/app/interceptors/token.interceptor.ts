import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { TokenService } from '../service/token.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  // constructor(private tokenService: TokenService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjA5Njk4NTg2MDMiLCJzdWIiOiIwOTY5ODU4NjAzIiwiZXhwIjoxNzI2NjMwNzIxfQ.3e28HseTr7a5OE0hXVG8Au_TKhVr4dV0bN8XEEsHF_M";
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    return next.handle(req);
  }
}