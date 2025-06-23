
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  addComment(comment: any) {
    return this.http.post('http://localhost:8080/comment', comment);
  }

  deleteComment(id: number) {
  return this.http.delete(`http://localhost:8080/comment/${id}`);
  }
}
