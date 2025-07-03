import { Component, OnInit } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import {ActivatedRoute,  RouterModule} from '@angular/router';


@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage, RouterModule ],
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];

  currentCategoryId: number | string | null = null;


  constructor(private _categoryService: CategoryService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.currentCategoryId = Number(id);
      }
    });

    this.route.url.subscribe(segments => {
      const isProducts = segments.some(seg => seg.path === 'products');
      if (isProducts) {
        this.currentCategoryId = 21;
      }
    });

    this._categoryService.getAll().subscribe({
      next: (data: any) => {
        this.categories = data;
        console.log(data);
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }


}
