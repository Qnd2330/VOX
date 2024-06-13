import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { TokenService } from '../service/token.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  // constructor(private tokenService: TokenService) { }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const token = "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjA5Njk4NTg2MDMiLCJzdWIiOiIwOTY5ODU4NjAzIiwiZXhwIjoxNzIwODk3MzQxfQ.8gvY57c_BsG8Yh6tzafodwrHEZfoqPwOwkNqLgXGYYI";
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