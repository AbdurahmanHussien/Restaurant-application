import {Component, EventEmitter, HostListener, Output} from '@angular/core';
import { CommonModule } from '@angular/common';
import {CardComponent} from '../card/card.component';
import {ActivatedRoute, Router, RouterLink, RouterLinkActive} from '@angular/router';
import {HeaderService} from '../../services/header.service';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../services/auth.service';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, CardComponent, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router: Router , private activatedRoute: ActivatedRoute, private loginService: AuthService) {}


  searchKey = '';



  search(searchKey:any) {


    const idCategoryExist = this.activatedRoute.firstChild?.snapshot.paramMap.has("id");

    if (idCategoryExist) {
      const idCategory = this.activatedRoute.firstChild?.snapshot.paramMap.get("id");
      this.router.navigateByUrl(`/searchInCategory/${idCategory}/search/${searchKey}`);
    } else {
      this.router.navigateByUrl(`products/search/${searchKey}`);
    }
  }

  get isLoggedIn() {
    return this.loginService.isLoggedIn;
  }
  logout() {
    localStorage.removeItem('jwt_token');

    this.router.navigate(['/login'],
      {
      queryParams: { loggedOut: 'true' }
    });
  }

}
