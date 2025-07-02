import {Component, OnInit} from '@angular/core';
import { NgForOf, NgIf} from "@angular/common";
import {UserOrdersService} from '../../services/user-orders.service';
import {AllUserOrders} from '../../models/allUserOrders';
import {TimeagoPipe} from '../../services/timeago.pipe';

@Component({
  selector: 'app-all-system-order',
  imports: [
    NgForOf,
    NgIf,
    TimeagoPipe
  ],
  templateUrl: './all-system-order.component.html',
  styleUrls: ['./all-system-order.component.css']
})
export class AllSystemOrderComponent implements OnInit{

  constructor(private userOrdersService: UserOrdersService) {}

  AllUserOrders: AllUserOrders[] = [];
  page = 1;
  size = 6;
  totalPages = 0;
  message: string = '';
  noResultsFound:boolean = false;

  ngOnInit(): void {
    this.getAllOrders(this.page);
  }

  getAllOrders(page:number){
    // @ts-ignore
    this.userOrdersService.getAllOrders(page, this.size).subscribe({
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
        this.message = 'there is no order yet';
      }
    })
  }

  previousPage() {
    if (this.page > 1) {
      this.page--;
      this.getAllOrders(this.page);
    }
  }

  nextPage() {
    if (this.page < this.totalPages ) {
      this.page++;
      this.getAllOrders(this.page);
    }
  }

}
