import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { UpdateBillDTO } from '../dtos/bill/update.bill.dtos';
import { ApiResponse } from '../responses/api.response';
import { InsertBillDTO } from '../dtos/bill/insert.bill.dtos';
import { BillRespones } from '../responses/bill/bill.response';
import { Bill } from '../models/bill';


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
  getBillById(id: number, page: number, limit: number): Observable<any> {
    const params = new HttpParams()
    .set('page', page.toString())
    .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/bill_details/list/${id}`, { params });
  }
  deleteBill(id: number): Observable<any> {
    debugger
    return this.http.delete<any>(`${this.apiBaseUrl}/bill/delete/${id}`);
  }

  updateBill(id: number, updateBill: UpdateBillDTO): Observable<any> {
    return this.http.put<any>(`${this.apiBaseUrl}/bill/update/${id}`, updateBill);
  }

  insertBill(insertBillDTO: InsertBillDTO): Observable<any> {
    return this.http.post<any>(`${this.apiBaseUrl}/bill/insert`, insertBillDTO, {
      headers: {
        'Content-Type': 'application/json'
      },
      responseType: 'json'
    }).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      // Client-side errors
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side errors
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      if (typeof error.error === 'string') {
        errorMessage += `\nServer Response: ${error.error}`;
      }
    }
    return throwError(errorMessage);
  }
}
