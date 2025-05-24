import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {



  isLoading: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  loading$ = this.isLoading.asObservable();


  show(){
    this.isLoading.next(true);

  }

  hide(){
    this.isLoading.next(false);
  }
}
