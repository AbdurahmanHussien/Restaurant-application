import { Component, OnInit } from '@angular/core';
import {CommonModule} from '@angular/common';
import { ProductService } from '../../services/product.service';
import {CategoryComponent} from '../category/category.component';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderCart} from '../../models/OrderCart';
import {OrderCartService} from '../../services/order-cart.service';



@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, CategoryComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})


export class ProductsComponent implements OnInit {

  products: any[] = [];
  page = 1; // first page
  size = 6;
  totalPages = 10; // changeable based on data rows from backend
  searchValue = '';
  isSearching = false;
  noResultsFound = false;
  categoryId: string = '';
  messageEn : string = '';


  constructor(private _productService: ProductService, private route: ActivatedRoute , private router: Router, private orderCartService: OrderCartService) {
  }




  ngOnInit() {
    this.route.params.subscribe(() => this.checkCategoryIdOrSearch());
    this.route.queryParams.subscribe(() => this.checkCategoryIdOrSearch());

  }



  loadProducts() {
    this._productService.getProducts(this.page, this.size).subscribe(data => {
      this.products = data.content;
      this.totalPages = data.totalPages ;
    });
  }

  previousPage() {
    if (this.page > 1) {
      this.page--;
      this.updateUrlAndLoad();
    }
  }

  nextPage() {
    if (this.page < this.totalPages ) {
      this.page++;
      this.updateUrlAndLoad();
    }
  }

  updateUrlAndLoad() {
    const queryParams = { page: this.page };
    let route: any[] = [];

    if (this.categoryId && this.isSearching) {
      route = ['/searchInCategory', this.categoryId, 'search', this.searchValue];
    } else if (this.categoryId) {
      route = ['/category', this.categoryId];
    } else if (this.isSearching) {
      route = ['products/search/', this.searchValue];
    } else {
      route = ['/products'];
    }

    this.router.navigate(route, {queryParams}).then(r =>
      this.loadPage());
  }



  loadPage() {
    if (this.isSearching) {
      this.getProducts(this.searchValue , this.page);
    } else if (this.categoryId) {
      this.getProductsByCategory(this.categoryId);
    } else {
      this.loadProducts();
    }
  }


  getProducts(term: string, page: number) {
    this.page = page;
    this.noResultsFound = false;

    this._productService.search(term, page, this.size).subscribe({
      next: data => {
        this.products = data.content;
        this.totalPages = data.totalPages;
        this.noResultsFound = this.products.length === 0;
        console.log('Searching for:', term);
      },
      error: error => {
        this.products = [];
        this.noResultsFound = true;
        this.messageEn = error.error.messages.message_en;
        console.log('Error', error);
      }
    });
  }

  getProductsByCategory(categoryId: string) {
    this._productService.loadProductsByCategory(categoryId, this.page, this.size).subscribe({
      next: data => {
        this.products = data.content;
        this.totalPages = data.totalPages;
        this.noResultsFound = this.products.length === 0;
        console.log('Category products:', this.products);
      },
      error: err => {
        this.products = [];
        this.noResultsFound = true;
        console.error(err);
      }
    });
  }

  checkCategoryIdOrSearch() {
    this.route.paramMap.subscribe(params => {
      this.route.queryParamMap.subscribe(queryParams => {
        this.page = +queryParams.get('page')! || 1;

        const hasCategory = params.has('id');
        const hasKey = params.has('key');

        if (hasCategory && hasKey) {
          const id = params.get('id')!;
          const key = params.get('key')!;
          this.categoryId = id;
          this.searchValue = key;
          this.isSearching = true;
          this.searchByCategoryIdAndKey(id, key, this.page);

        } else if (hasCategory) {
          const id = params.get('id')!;
          this.categoryId = id;
          this.isSearching = false;
          this.getProductsByCategory(id);

        } else if (hasKey) {
          const key = params.get('key')!;
          this.isSearching = true;
          this.searchValue = key;
          this.getProducts(key ,this.page);

        } else {
          this.isSearching = false;
          this.categoryId = '';
          this.loadProducts();
        }
      });
    });
  }


  searchByCategoryIdAndKey(categoryId: string, searchValue: string, pageNumber: number) {
    this._productService.searchInCategory(categoryId, searchValue, pageNumber, this.size).subscribe({
      next: (data) => {
        this.products = data.content;
        this.totalPages = data.totalPages;
        this.noResultsFound = this.products.length === 0;
      },
      error: (error) => {
        this.products = [];
        this.noResultsFound = true;
        this.messageEn = error?.error?.messages?.message_en || 'Error occurred.';
        console.error(error);
      }
    });
  }


  addToCart(product: any) {
    const orderCart = new OrderCart(product);
    this.orderCartService.addToCart(orderCart);
  }

}
