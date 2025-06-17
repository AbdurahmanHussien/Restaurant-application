export interface AllUserOrders {
  id?: number;
  code: string;
  totalPrice: number;
  totalQuantity: number;
  productsNames: string[];
}
