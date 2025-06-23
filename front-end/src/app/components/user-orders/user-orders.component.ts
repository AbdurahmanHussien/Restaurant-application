import {Component, OnInit} from '@angular/core';
import { NgForOf, NgIf} from '@angular/common';
import {UserOrdersService} from '../../services/user-orders.service';
import {AllUserOrders} from '../../models/allUserOrders';
import {TimeagoPipe} from '../../services/timeago.pipe';

@Component({
  selector: 'app-user-orders',
  imports: [
    NgForOf,
    NgIf,
    TimeagoPipe

  ],
  templateUrl: './user-orders.component.html',
  styleUrl: './user-orders.component.css'
})
export class UserOrdersComponent implements OnInit {

  constructor(private userOrdersService: UserOrdersService) {}

  AllUserOrders: AllUserOrders[] = [];
  page = 1;
  size = 6;
  totalPages = 10;
  message: string = '';
  noResultsFound:boolean = false;

  ngOnInit(): void {
    this.getUserOrders(this.page);
    }

    getUserOrders(page:number){
    debugger
      // @ts-ignore
      let userId:number = localStorage.getItem('userId');
       this.userOrdersService.getUserOrders(userId, page, this.size).subscribe({
         next: (res) => {
           if (!res.content || res.content.length === 0) {
             this.noResultsFound = true;
             this.message = 'You have no orders yet';
             this.AllUserOrders = [];
             this.totalPages = 0;
           } else {
             this.noResultsFound = false;
             this.AllUserOrders = res.content;
             this.totalPages = res.totalPages;
           }
         },
         error: () => {
           this.noResultsFound = true;
           this.message = 'You have no orders yet';
         }
       })
    }

    previousPage() {
    if (this.page > 1) {
      this.page--;
      this.getUserOrders(this.page);
    }
  }

    nextPage() {
    if (this.page < this.totalPages ) {
      this.page++;
      this.getUserOrders(this.page);
    }
  }

}
