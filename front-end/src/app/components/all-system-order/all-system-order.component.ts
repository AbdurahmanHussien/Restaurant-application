import {Component, OnInit} from '@angular/core';
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {UserOrdersService} from '../../services/user-orders.service';
import {AllUserOrders} from '../../models/allUserOrders';

@Component({
  selector: 'app-all-system-order',
  imports: [
    NgForOf,
    NgIf,
    DatePipe
  ],
  templateUrl: './all-system-order.component.html',
  styleUrl: './all-system-order.component.css'
})
export class AllSystemOrderComponent implements OnInit{

  constructor(private userOrdersService: UserOrdersService) {}

  AllUserOrders: AllUserOrders[] = [];
  page = 1;
  size = 6;
  totalPages = 10;
  message: string = '';
  noResultsFound:boolean = false;

  ngOnInit(): void {
    this.getAllOrders(this.page);
  }

  getAllOrders(page:number){
    // @ts-ignore
    let userId:number = localStorage.getItem('userId');
    this.userOrdersService.getAllOrders(page, this.size).subscribe({
      next: (res) => {
        this.noResultsFound = false;
        this.AllUserOrders = res.content;
        this.totalPages = res.totalPages;
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
