import { Component } from '@angular/core';
import {NgIf} from '@angular/common';
import {LoaderService} from '../../services/loader.service';

@Component({
  selector: 'app-loader',
  imports: [
    NgIf
  ],
  templateUrl: './loader.component.html',
  styleUrl: './loader.component.css'
})
export class LoaderComponent {

  showLoader = false;

  constructor(private _loaderService: LoaderService) {
    this._loaderService.isLoading.subscribe(
      (value) => (this.showLoader = value)
    )

  }

}
