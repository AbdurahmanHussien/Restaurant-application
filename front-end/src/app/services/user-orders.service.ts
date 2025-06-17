import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserOrdersService {

  constructor(private http: HttpClient) { }


   private baseUrl = 'http://localhost:8080/api/orders'

  getUserOrders(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
