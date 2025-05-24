import { Component, OnInit } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { ProductService } from '../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SharedService} from '../../services/shared.service';
import {LoaderComponent} from '../loader/loader.component';



@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage, LoaderComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})


export class ProductsComponent implements OnInit {

  products: any[] = [];
  page = 0;
  size = 8;
  totalPages = 7;
  searchValue = '';
  isSearching = false;
  noResultsFound = false;


  constructor(private _productService: ProductService, private route: ActivatedRoute, private shared: SharedService , private router : Router) {}

  showLoader = false;

  ngOnInit() {

    this.shared.search$.subscribe(val => {
      this.searchValue = val;
      this.page = 0;

      if (val && val.trim() !== '') {
        this.isSearching = true;
        this.getProducts(val);
      } else {
        this.isSearching = false;
        this.noResultsFound = false;
          this.page = 0;
          this.loadProducts();
        }
    });

    if (!this.searchValue) {
      this.loadProducts();
    }
  }

  loadProducts() {
    this._productService.getProducts(this.page, this.size).subscribe(data => {
      this.products = data.content;
      this.totalPages = data.totalPages;
    });
  }

  previousPage() {
    if (this.page > 0) {
      this.page--;
      this.loadPage();
    }
  }

  nextPage() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadPage();
    }
  }

  loadPage() {
    if (this.isSearching) {
      this.getProducts(this.searchValue);
    } else {
      this.loadProducts();
    }
  }

  getProducts(term: string) {
    this.noResultsFound = false;

    this._productService.search(term, this.page, this.size).subscribe({
      next : data=> {
      this.products = data.content;
      this.totalPages = data.totalPages;
      this.noResultsFound = this.products.length === 0;
      console.log('Searching for:', term);
    },
      error : error => {
        this.products=[];
        this.noResultsFound = true;
        console.log('Error', error);
      }
      });
  }




}
