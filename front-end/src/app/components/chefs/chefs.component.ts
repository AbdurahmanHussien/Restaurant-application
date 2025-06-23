import { Component, OnInit } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Chef } from '../../models/chef';
import {ChefService} from '../../services/chef.service';

@Component({
  selector: 'app-chefs',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage],
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {

  chefs: Chef[] = [];

  constructor(private http: HttpClient , private chefService: ChefService) { }

  ngOnInit(): void {
    this.chefService.getChefs().subscribe(chef => {
      this.chefs = chef;
    })
  }
  updateRating(chef: Chef, newRating: number) {
    chef.rating = newRating;
  }

}
