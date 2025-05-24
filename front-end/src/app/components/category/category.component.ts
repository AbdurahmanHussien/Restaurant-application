import { Component, OnInit } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../models/category';
import {RouterLinkActive, RouterModule} from '@angular/router';


@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage, RouterLinkActive , RouterModule],
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  selectedTab : Category | undefined;

  constructor(private _categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this._categoryService.getAll().subscribe({
      next: (data: any) => {
        this.categories = data;
        if(this.categories.length>0){
          this.selectedTab = this.categories[0];

        }
        console.log(data);
      },
      error: (err: any) => {
        console.log(err);
      }
    })
  }


}
