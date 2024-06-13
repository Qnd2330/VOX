import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { UserResponse } from '../responses/user/user.response';
import { ApiResponse } from '../responses/api.response';
import { HttpUtilService } from './http.util.service';
import { RegisterDTO } from '../dtos/user/register.dtos';
import { LoginDTO } from '../dtos/user/login.dtos';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiBaseUrl = environment.apiBaseUrl;
  private apiRegister = `${environment.apiBaseUrl}/user/register`;
  private apiLogin = `${environment.apiBaseUrl}/user/login`;

  private httpUtilService = inject(HttpUtilService);  

  localStorage?:Storage;

  private apiConfig = {
    headers: this.httpUtilService.createHeaders(),
  }

  constructor(private http: HttpClient) { }

  getUsers(keyword: string, page: number, limit: number):Observable<any>{
    const params = new HttpParams()
    .set('keyword', keyword)
    .set('page', page.toString())
    .set('limit', limit.toString());
    return this.http.get<any>(`${environment.apiBaseUrl}/user/list`, {params});   
  }
  getDetailUser(userID: number): Observable<any> {
    return this.http.get<any>(`${this.apiBaseUrl}/user/get/${userID}`);
  }
  deleteUser(userID: number): Observable<any> {
    return this.http.delete<any>(`${this.apiBaseUrl}/user/delete/${userID}`);
  }

  updateUser(id: number, updateUser: User): Observable<any> {
    return this.http.put<any>(`${this.apiBaseUrl}/user/update/${id}`, updateUser);
  }

  insertUser(insertUser: User): Observable<any> {
    return this.http.post<any>(`${this.apiBaseUrl}/user/register`, insertUser, {
    }
    );
  }
  
  saveUserResponseToLocalStorage(userResponse?: UserResponse) {
    try {
      debugger
      if(userResponse == null || !userResponse) {
        return;
      }
      // Convert the userResponse object to a JSON string
      const userResponseJSON = JSON.stringify(userResponse);  
      // Save the JSON string to local storage with a key (e.g., "userResponse")
      this.localStorage?.setItem('user', userResponseJSON);  
      console.log('User response saved to local storage.');
    } catch (error) {
      console.error('Error saving user response to local storage:', error);
    }
  }
  getUserResponseFromLocalStorage():UserResponse | null {
    try {
      // Retrieve the JSON string from local storage using the key
      const userResponseJSON = this.localStorage?.getItem('user'); 
      if(userResponseJSON == null || userResponseJSON == undefined) {
        return null;
      }
      // Parse the JSON string back to an object
      const userResponse = JSON.parse(userResponseJSON!);  
      console.log('User response retrieved from local storage.');
      return userResponse;
    } catch (error) {
      console.error('Error retrieving user response from local storage:', error);
      return null; // Return null or handle the error as needed
    }
  }
  removeUserFromLocalStorage():void {
    try {
      // Remove the user data from local storage using the key
      this.localStorage?.removeItem('user');
      console.log('User data removed from local storage.');
    } catch (error) {
      console.error('Error removing user data from local storage:', error);
      // Handle the error as needed
    }
  }

  resetPassword(userId: number): Observable<ApiResponse> {
    const url = `${environment.apiBaseUrl}/users/reset-password/${userId}`;
    return this.http.put<ApiResponse>(url, null, this.apiConfig);
  }
  
  register(registerDTO: RegisterDTO):Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.apiRegister, registerDTO, this.apiConfig);
  }

  login(loginDTO: LoginDTO): Observable<ApiResponse> {    
    return this.http.post<ApiResponse>(this.apiLogin, loginDTO, this.apiConfig, );
  }

  getUserDetail(token: string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.apiLogin, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      })
    })
  }
}
