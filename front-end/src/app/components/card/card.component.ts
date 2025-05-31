import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {OrderCartService} from '../../services/order-cart.service';

@Component({
  selector: 'app-card',
  standalone: true,
  templateUrl: './card.component.html',
  imports: [
    RouterLink
  ],
  styleUrls: ['./card.component.css']
})

export class CardComponent {

  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private orderCartService: OrderCartService) {
  }
  ngOnInit(): void {
    this.orderCartService.totalPrice.subscribe(
      value => this.totalPrice = value
    )

    this.orderCartService.totalQuantity.subscribe(
      value => this.totalQuantity = value
    )
  }

}
