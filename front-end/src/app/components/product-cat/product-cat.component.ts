import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CurrencyPipe, NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-product-cat',
  standalone: true,
  imports: [
    CurrencyPipe,
    NgOptimizedImage
  ],
  templateUrl: './product-cat.component.html',
  styleUrls: ['./product-cat.component.css']
})
export class ProductCatComponent implements OnInit {

  products: any[] = []
  categoryId:String = '';

 constructor(private _productService: ProductService, private route: ActivatedRoute, private router: Router) {
 }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.categoryId = params['id'];
      console.log('categoryId:', this.categoryId);

      if (this.categoryId === '21') {
        this.router.navigate(['/products']);
        return;
      } else {
        this.getProductsByCategory(this.categoryId);
      }
    });
  }


  getProductsByCategory(categoryId: String) {

    this._productService.loadProductsByCategory(categoryId).subscribe({

           next: data => {
          this.products = data;
          console.log(this.products);
        }
      })
  }


}
