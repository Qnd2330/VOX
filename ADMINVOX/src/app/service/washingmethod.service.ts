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
    return this.http.get<any>(`${this.apiBaseUrl}/washing_method/list`, { params });
  }
}
