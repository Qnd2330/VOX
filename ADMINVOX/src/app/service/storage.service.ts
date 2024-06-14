import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { storages } from '../models/storage';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private apiBaseUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getAllStorage(page: number, limit: number): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/storage/list`, { params });
  }

  deleteStorage(id: number): Observable<any> {
    debugger
    return this.http.delete<any>(`${this.apiBaseUrl}/storage/delete/${id}`);
  }

  insertStorage(insertStorageDTO: storages): Observable<any> {
    return this.http.post<any>(`${this.apiBaseUrl}/storage/insert`, insertStorageDTO, {
    }
    );
  }

  getStorageByID(id: number): Observable<any> {
    return this.http.get<any>(`${environment.apiBaseUrl}/storage/${id}`);
  }

  updateStorage(id: number, updateStorage: storages): Observable<any> {
    return this.http.put<any>(`${this.apiBaseUrl}/storage/update/${id}`, updateStorage);
  }
}
