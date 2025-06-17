import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { ToastrService } from 'ngx-toastr';
import { CategoryService } from '../../services/category.service';
import { ActivatedRoute } from '@angular/router';
import {FormsModule} from '@angular/forms';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-product-action',
  templateUrl: './product-action.component.html',
  imports: [
    FormsModule,
    NgForOf
  ],
  styleUrl: './product-action.component.css'
})
export class ProductActionComponent implements OnInit {

  constructor(
    private productService: ProductService,
    private toastr: ToastrService,
    private categoryService: CategoryService,
    private route: ActivatedRoute
  ) {}

  product = {
    id: 0,
    name: '',
    imagePath: '',
    description: '',
    price: 0,
    categoryId: ''
  };

  categories = [];
  isEditMode = false;

  ngOnInit(): void {
    this.getCategories();

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.isEditMode = true;
      const id = Number(idParam);
      this.productService.getProduct(id).subscribe(data => {
        this.product = data;
      });
    }
  }

  getCategories() {
    this.categoryService.getAll().subscribe(data => {
      this.categories = data;
    });
  }

  saveProduct() {
    if (this.isEditMode) {

      this.productService.updateProduct( this.product).subscribe(() => {
        this.toastr.success('Product updated successfully');
      });
    } else {
      this.productService.addProduct(this.product).subscribe(() => {
        this.toastr.success('Product added successfully');
      });
    }
  }
}
