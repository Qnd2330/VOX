import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BillService {
  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getBill(page: number, limit: number):Observable<any>{
    const params = new HttpParams()
    .set('page', page.toString())
    .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/bill/list`, { params });   
  }
  getDetailBill(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}/bill/${id}`);
  }
  deleteBill(id: number): Observable<any> {
    debugger
    return this.http.delete<any>(`${this.apiBaseUrl}/bill/${id}`);
  }
  
}
