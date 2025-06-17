import { Injectable } from '@angular/core';
import { OrderCart } from '../models/OrderCart';
import { BehaviorSubject, Subject } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {OrderItem} from '../models/orderItem';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class OrderCartService {

  private apiUrl = 'http://localhost:8080/api/orders';


  constructor(private http: HttpClient, private toastr: ToastrService) {
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

  addOrderToDatabase() {
    const totalPrice = this.orderCart.reduce((sum, item) => sum + item.price * item.quantity, 0);
    const totalQuantity = this.orderCart.reduce((sum, item) => sum + item.quantity, 0);

    const products = this.orderCart.map(item => ({
      productId: item.id,
      quantity: item.quantity,
      price: item.price
    }));

    const orderItem: OrderItem = {
      totalPrice: totalPrice.toString(),
      totalQuantity: totalQuantity.toString(),
      products: products
    };

    this.http.post(`${this.apiUrl}`, orderItem ).subscribe({
      next: () => {
        this.orderCart = [];
        localStorage.removeItem('orderCart');
        this.computeCartTotals();
        this.saveCartToStorage();
        this.toastr.success('Your order has been confirmed', 'Success');
        setTimeout(() => {
          window.location.reload();
        }, 1000);
      },
      error: error => {
        console.error('Failed to add order to database:', error);
      }
    });

  }

}
