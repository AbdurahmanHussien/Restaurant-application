import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-card',
  standalone: true,
  templateUrl: './card.component.html',
  imports: [
    RouterLink
  ],
  styleUrls: ['./card.component.css']
})

export class CardComponent {

}
