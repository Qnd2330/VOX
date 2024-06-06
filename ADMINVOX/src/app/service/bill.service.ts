import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UpdateBillDTO } from '../dtos/bill/update.bill.dtos';
import { ApiResponse } from '../responses/api.response';


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
  getBillById(id: number, page: number, limit: number): Observable<ApiResponse> {
    const params = new HttpParams()
    .set('page', page.toString())
    .set('limit', limit.toString());
    return this.http.get<ApiResponse>(`${environment.apiBaseUrl}/bill_details/list/${id}`, { params });
  }
  deleteBill(id: number): Observable<any> {
    debugger
    return this.http.delete<any>(`${this.apiBaseUrl}/bill/${id}`);
  }

  updateBill(id: number, updateBill: UpdateBillDTO): Observable<any> {
    return this.http.put<any>(`${this.apiBaseUrl}/qldg/${id}`, updateBill);
  }
  
}
