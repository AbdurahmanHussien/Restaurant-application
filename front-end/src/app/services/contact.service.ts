import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private apiUrl = 'http://localhost:8080/api/contact';

  constructor(private http: HttpClient) {}

  sendMessage(data: any): Observable<any> {

    return this.http.post(this.apiUrl, data);
  }

  getMessages(): Observable<any> {   //http://localhost:8080/api/contact/user
    return this.http.get(`${this.apiUrl}/user`);

  }


  getAllContacts() {
    return this.http.get(`${this.apiUrl}`);
  }
}
