import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {LoaderComponent} from './components/loader/loader.component';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent, LoaderComponent, NgIf],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']

})
export class AppComponent  {

    scrollToTop() {
      window.scrollTo({
        top: 0,
        behavior: 'smooth' });
    }

  isLoginPage = false;

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.isLoginPage = this.router.url === '/login' || this.router.url === '/signup' || this.router.url === '/login?loggedOut=true';
    });
  }

}
