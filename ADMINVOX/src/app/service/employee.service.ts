import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getUsersByRole(roleID: string, page: number, limit: number): Observable<any> {
    const params = new HttpParams()
      .set('roleID', roleID)
      .set('page', page.toString())
      .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/user/list`, { params });
  }
  getDetailUser(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}/user/${id}`);
  }
  deleteUser(id: number): Observable<any> {
    debugger
    return this.http.delete<any>(`${this.apiBaseUrl}/user/${id}`);
  }
  
}
