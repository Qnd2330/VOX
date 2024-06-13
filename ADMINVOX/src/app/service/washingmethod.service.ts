import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Wash } from '../models/wash_method';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WashingmethodService {

  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getAllWashes(page: number, limit: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/washing_method/list`, { params });
  }

  getWashByID(id: number): Observable<any> {
    return this.http.get<any>(`${environment.apiBaseUrl}/washing_method/${id}`);
  }

  deleteWash(id: number): Observable<any> {
    debugger
    return this.http.delete<any>(`${this.apiBaseUrl}/washing_method/delete/${id}`);
  }

  updateWash(id: number, updateWash: Wash): Observable<any> {
    return this.http.put<any>(`${this.apiBaseUrl}/washing_method/update/${id}`, updateWash);
  }

  insertWash(insertWashDTO: Wash): Observable<any> {
    return this.http.post<any>(`${this.apiBaseUrl}/washing_method/insert`, insertWashDTO, {
    }
    );
  }
}
