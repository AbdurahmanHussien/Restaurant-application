import {SingleOrderItem} from './singleOrderItem';

export interface OrderItem {
  id?: number;
  code?: string;
  totalPrice: string;
  totalQuantity: string;
  products: SingleOrderItem[];
  userName?: string;

}
