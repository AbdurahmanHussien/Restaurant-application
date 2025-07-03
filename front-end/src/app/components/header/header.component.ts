import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {CardComponent} from '../card/card.component';
import {ActivatedRoute, Router, RouterLink, RouterLinkActive} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../services/auth.service';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, CardComponent, RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements  OnInit {

  constructor(
              private activatedRoute: ActivatedRoute,
              private loginService: AuthService,
              private router: Router) {
  }

  dropdownOpenAccount = false;
  dropdownOpenAdmin = false;


   ngOnInit(): void {
    let token = localStorage.getItem("jwt_token");
    let payload = this.getPayload(token);
    this.username = payload.username;
    console.log(this.username);
    }



  searchKey = '';

  username: string | undefined;


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
    localStorage.removeItem('roles');
    localStorage.removeItem('userId');
    localStorage.removeItem('orderCart');
    localStorage.removeItem('theme')

    this.router.navigate(['/login'],
      {
      queryParams: { loggedOut: 'true' }
    });
  }


  getPayload(token: string | null) {
    // @ts-ignore
    const payloadBase64 = token.split('.')[1];
    const decodedPayload = atob(payloadBase64);
    return JSON.parse(decodedPayload);
  }

  toggleDropdownAccount() {
    this.dropdownOpenAccount = !this.dropdownOpenAccount;
  }

  toggleDropdownAdmin() {
    this.dropdownOpenAdmin = !this.dropdownOpenAdmin;
  }

  isUserAdmin(): boolean {
    return this.loginService.isUserAdmin();
  }


}
