import {Injectable} from '@angular/core';
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
   isUserAdmin(): boolean{
    return localStorage.getItem('roles') === 'ADMIN';
   }

  getCurrentUser() {
    return localStorage.getItem('userId');
  }

  getCurrentUserName(): string {
    const token = localStorage.getItem('jwt_token');
    if (!token) return '';

    const payloadBase64 = token.split('.')[1];
    const payload = JSON.parse(atob(payloadBase64));

    return payload.username;
  }

}
