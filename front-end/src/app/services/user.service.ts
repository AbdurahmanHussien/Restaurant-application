import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  private baseUrl = 'http://localhost:8080/api/user'


  getUserData(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateUserData(id: number, userData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, userData);
  }
}
