import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {UserOrdersService} from '../../services/user-orders.service';
import {AllUserOrders} from '../../models/allUserOrders';

@Component({
  selector: 'app-user-orders',
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './user-orders.component.html',
  styleUrl: './user-orders.component.css'
})
export class UserOrdersComponent implements OnInit {

  constructor(private userOrdersService: UserOrdersService) {}

  AllUserOrders: AllUserOrders[] = [];
  message: string = '';

  ngOnInit(): void {
    this.getUserOrders();
    }

    getUserOrders(){
    debugger
      // @ts-ignore
      let userId:number = localStorage.getItem('userId');
       this.userOrdersService.getUserOrders(userId).subscribe({
         next: (res) => {
           this.AllUserOrders = res;
         },
         error: (err) => {
           this.message = 'You have no orders yet';
         }
       })
    }


}
