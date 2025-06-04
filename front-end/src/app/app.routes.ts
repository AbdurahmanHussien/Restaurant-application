import {ExtraOptions, Routes} from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';
import { ContactInfoComponent } from './components/contact-info/contact-info.component';
import { ChefsComponent } from './components/chefs/chefs.component';
import {LoginComponent} from './components/login/login.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {AuthGuard} from './services/AuthGuard';



export const routes: Routes = [
  { path: 'products', component: ProductsComponent , title: "Home" ,canActivate: [AuthGuard] },
  {path : 'category/:id', component: ProductsComponent , title: "Category",  canActivate: [AuthGuard] },
  {path : 'login', component: LoginComponent , title: "Login" },
  {path : 'signup', component: SignUpComponent , title: "Sign up" },
  {path : 'products/search/:key', component: ProductsComponent , title: "search" },
  {path : 'searchInCategory/:id/search/:key', component: ProductsComponent , title: "search" },
  { path: 'cardDetails', component: CardDetailsComponent , title: "Card Details" ,  canActivate: [AuthGuard] },
  { path: 'contact-info', component: ContactInfoComponent , title: "Contact Info" ,  canActivate: [AuthGuard] },
  { path: 'chefs', component: ChefsComponent , title: "Teams" ,  canActivate: [AuthGuard] },
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: '**', redirectTo: '/products', pathMatch: 'full' }
];

const routerOptions: ExtraOptions = {
  scrollPositionRestoration: 'enabled'
};
