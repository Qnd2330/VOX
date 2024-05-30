import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getUsers(keyword: string, page: number, limit: number):Observable<any>{
    const params = new HttpParams()
    .set('keyword', keyword)
    .set('page', page.toString())
    .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/user/list`, {params});   
  }
  getDetailUser(userID: number): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}/user/${userID}`);
  }
  deleteUser(userID: number): Observable<any> {
    return this.http.delete<any>(`${this.apiBaseUrl}/user/delete/${userID}`);
  }
  
}
