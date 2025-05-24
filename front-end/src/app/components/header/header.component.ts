import {Component, EventEmitter, HostListener, Output} from '@angular/core';
import { CommonModule } from '@angular/common';
import {CardComponent} from '../card/card.component';
import {Router, RouterLink, RouterLinkActive} from '@angular/router';
import {ProductService} from '../../services/product.service';
import {HeaderService} from '../../services/header.service';
import {FormsModule} from '@angular/forms';
import {__propKey} from 'tslib';
import {SharedService} from '../../services/shared.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, CardComponent, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private  headerService: HeaderService , private shared : SharedService ) {}

  products: any []=[];

  searchValue = '';

  onSearchClick() {
    let term = this.searchValue.trim();
    this.shared.updateSearch(term);
  }




}
