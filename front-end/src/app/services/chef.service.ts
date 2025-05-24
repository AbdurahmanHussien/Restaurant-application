
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chef } from '../models/chef';

@Injectable({
  providedIn: 'root'
})
export class ChefService {

  private apiUrl = 'http://localhost:8080/api/chef';

  constructor(private http: HttpClient) {}

  getChefs(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
