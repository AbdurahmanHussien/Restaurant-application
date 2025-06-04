import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private http: HttpClient) {}


  login(data: any): Observable<any> {
    return this.http.post('http://localhost:8080/auth/login', data);
  }

  signup(data: any): Observable<any> {
    return this.http.post('http://localhost:8080/auth/signup', data);
  }


  isLoggedIn(): boolean {
    return !!localStorage.getItem('jwt_token');
  }
}
