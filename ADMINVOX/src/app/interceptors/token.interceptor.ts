import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../service/token.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const token = "eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjA5Njk4NTg2MDMiLCJzdWIiOiIwOTY5ODU4NjAzIiwiZXhwIjoxNzE5MDg1NjI4fQ.msfoOFigltLo4J0-sZQ9_VGUw3qQatyZH2dpxdqkMwE";
  
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