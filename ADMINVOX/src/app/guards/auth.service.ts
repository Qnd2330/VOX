import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = 'http://localhost:2330/VOX/user/login';
    private tokenKey = 'token';

    constructor(private http: HttpClient, @Inject(PLATFORM_ID) private platformId: Object) { }

    login(phoneNumber: string, userPassword: string): Observable<any> {
        const body = { phoneNumber, userPassword };
        return this.http.post<any>(this.apiUrl, body);
    }

    setToken(token: string): void {
        if (isPlatformBrowser(this.platformId)) {
            localStorage.setItem(this.tokenKey, token);
        }
    }

    getToken(): string | null {
        if (isPlatformBrowser(this.platformId)) {
            return localStorage.getItem(this.tokenKey);
        }
        return null;
    }

    isLoggedIn(): boolean {
        return this.getToken() !== null;
    }

    logout(): void {
        if (isPlatformBrowser(this.platformId)) {
            localStorage.removeItem(this.tokenKey);
        }
    }
}
