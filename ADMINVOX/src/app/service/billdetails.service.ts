import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { InsertBillDetailDTO } from '../dtos/billdetail/insert.billdetail.dtos';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BilldetailsService {
  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  insertBillDetail(billID: number, insertBillDetailDTO: InsertBillDetailDTO): Observable<any> {
    return this.http.post<any>(`${this.apiBaseUrl}/bill_details/insert/${billID}`, insertBillDetailDTO, {
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
