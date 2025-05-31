import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Category} from '../models/category';



@Injectable({ providedIn: 'root' })


export class CategoryService {
  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/api/category';

  getAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
