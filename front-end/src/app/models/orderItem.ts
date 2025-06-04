import {Product} from './product';

export interface OrderItem {
  id?: number;
  code?: string;
  totalPrice: string;
  totalQuantity: string;
  productIds: number[];


}
