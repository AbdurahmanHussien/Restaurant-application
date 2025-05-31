import { Injectable } from '@angular/core';
import { OrderCart } from '../models/OrderCart';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderCartService {

  constructor() {
    const storedCart = localStorage.getItem('orderCart');
    if (storedCart) {
      this.orderCart = JSON.parse(storedCart);
      this.computeCartTotals();
    }
  }

  orderCart: OrderCart[] = [];
  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  addToCart(orderCart: OrderCart) {
    let existingOrderCart = this.orderCart.find(item => item.id === orderCart.id);

    if (existingOrderCart) {
      existingOrderCart.quantity++;
    } else {
      this.orderCart.push(orderCart);
    }

    this.computeCartTotals();
    this.saveCartToStorage();
  }

  private computeCartTotals() {
    let totalPriceValue = 0;
    let totalQuantityValue = 0;

    for (let item of this.orderCart) {
      totalPriceValue += item.price * item.quantity;
      totalQuantityValue += item.quantity;
    }

    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }

  decrementQuantity(orderCart: OrderCart) {
    orderCart.quantity--;

    if (orderCart.quantity === 0) {
      this.remove(orderCart);
    } else {
      this.computeCartTotals();
      this.saveCartToStorage();
    }
  }

  remove(orderCart: OrderCart) {
    const index = this.orderCart.findIndex(item => item.id === orderCart.id);
    if (index > -1) {
      this.orderCart.splice(index, 1);
      this.computeCartTotals();
      this.saveCartToStorage();
    }
  }

  private saveCartToStorage() {
    localStorage.setItem('orderCart', JSON.stringify(this.orderCart));
  }
}
