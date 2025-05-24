import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({ providedIn: 'root' })


export class ProductService {
  constructor(private http: HttpClient) {}


  getAll(): Observable<any> {
    return this.http.get('http://localhost:8080/api/product');
  }


  getProducts(page: number, size: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/product?page=${page}&size=${size}`);
  }

  search(keyword:String , page: number, size: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/product/search?keyword=${keyword}&page=${page}&size=${size}`);
  }


  loadProductsByCategory(categoryId: String): Observable<any> {
    return this.http.get(`http://localhost:8080/api/product/byCatId?categoryId=${categoryId}`);
  }

  //http://localhost:8080/api/product/search?
  //     keyword={{$random.alphanumeric(8)}}&
  //     page={{$random.integer(100)}}&
  //     size={{$random.integer(100)}}
}
