import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {OrderCart} from '../../models/OrderCart';
import {OrderCartService} from '../../services/order-cart.service';

@Component({
  selector: 'app-card-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-details.component.html',
  styleUrls: ['./card-details.component.css']
})
export class CardDetailsComponent implements OnInit {



  orders: OrderCart[] = [];

  constructor(private orderCartService: OrderCartService) { }

  ngOnInit(): void {
    this.orders = this.orderCartService.orderCart
  }

  incrementQuantity(orderCart: OrderCart) {
    this.orderCartService.addToCart(orderCart);
  }

  decrementQuantity(orderCart: OrderCart) {
    this.orderCartService.decrementQuantity(orderCart);
  }

  remove(orderCart: OrderCart) {
    this.orderCartService.remove(orderCart);
  }

  confirmOrder() {

    this.orderCartService.addOrderToDatabase();

  }



}
