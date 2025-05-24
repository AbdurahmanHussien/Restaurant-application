import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private searchSource = new BehaviorSubject<string>('');
  search$ = this.searchSource.asObservable();

  updateSearch(term: string) {
    this.searchSource.next(term);
  }
}
