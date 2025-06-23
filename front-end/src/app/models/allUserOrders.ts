export interface AllUserOrders {
  id?: number;
  code: string;
  userName?: string;
  totalPrice: number;
  totalQuantity: number;
  createdAt :Date;

  items: {
    productName: string;
    quantity: number;
    price: number;
  }[];
}
