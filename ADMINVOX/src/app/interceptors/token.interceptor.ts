import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../service/token.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const token = "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjAzNTQ1Mzk4NTQiLCJzdWIiOiIwMzU0NTM5ODU0IiwiZXhwIjoxNzIwNDMzODc2fQ.ETf6t6UfHOoiKkjV5dwQgvquV6v1COuQhfgOFEJQLh0";
  
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