import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({ providedIn: 'root' })


export class ProductService {
  constructor(private http: HttpClient) {}



  baseUrl = 'http://localhost:8080/api/product';



  getProducts(page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}?page=${page}&size=${size}`);
  }

  search(keyword:String , page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/search?keyword=${keyword}&page=${page}&size=${size}`);
  }


  loadProductsByCategory(categoryId: String  , page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/byCatId?categoryId=${categoryId}&page=${page}&size=${size}`);
  }

  searchInCategory(categoryId: String ,keyword:String , page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/searchByCatId?categoryId=${categoryId}&keyword=${keyword}&page=${page}&size=${size}`);
  }

  //http://localhost:8080/api/product/searchByCatId?categoryId=6&keyword=df&page=3&size=5


  //http://localhost:8080/api/product/search?
  //     keyword={{$random.alphanumeric(8)}}&
  //     page={{$random.integer(100)}}&
  //     size={{$random.integer(100)}}
}
