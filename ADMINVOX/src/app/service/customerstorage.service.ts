import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InsertCusStorageDTO } from '../dtos/cusstorage/insert.cusstorage.dtos';

@Injectable({
  providedIn: 'root'
})
export class CustomerstorageService {
  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getListCustomerStorage(page: number, limit: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/customerstorage/list`, { params });
  }

  insertCusStorage(InsertCusStorageDTO: InsertCusStorageDTO): Observable<any> {
    return this.http.post<any>(`${this.apiBaseUrl}/customerstorage/insert`, InsertCusStorageDTO)}
}
