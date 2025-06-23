import {ExtraOptions, Routes} from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';
import { ContactInfoComponent } from './components/contact-info/contact-info.component';
import { ChefsComponent } from './components/chefs/chefs.component';
import {LoginComponent} from './components/login/login.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';
import {AuthGuard} from './services/AuthGuard';
import {GuestGuard} from './services/GuestGuard';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {UserOrdersComponent} from './components/user-orders/user-orders.component';
import {ProductActionComponent} from './components/product-action/product-action.component';
import {AllSystemOrderComponent} from './components/all-system-order/all-system-order.component';
import {MyMessagesComponent} from './components/my-messages/my-messages.component';
import {AdminContactInfoComponent} from './components/admin-contact-info/admin-contact-info.component';



export const routes: Routes = [
  { path: 'products', component: ProductsComponent , title: "Home" ,canActivate: [AuthGuard] },
  {path : 'category/:id', component: ProductsComponent , title: "Category",  canActivate: [AuthGuard] },
  {path : 'login', component: LoginComponent , title: "Login", canActivate: [GuestGuard] },
  {path : 'signup', component: SignUpComponent , title: "Sign up" , canActivate: [GuestGuard] },
  {path : 'products/search/:key', component: ProductsComponent , title: "search" },
  {path : 'searchInCategory/:id/search/:key', component: ProductsComponent , title: "search" },
  { path: 'cardDetails', component: CardDetailsComponent , title: "Card Details" ,  canActivate: [AuthGuard] },
  { path: 'contact-info', component: ContactInfoComponent , title: "Contact Info" ,  canActivate: [AuthGuard] },
  { path: 'chefs', component: ChefsComponent , title: "Teams" ,  canActivate: [AuthGuard] },
  { path: 'profile', component: UserProfileComponent , title: "profile" ,  canActivate: [AuthGuard] },
  { path: 'my-orders', component: UserOrdersComponent , title: "my orders" ,  canActivate: [AuthGuard] },
  { path: 'all-orders', component: AllSystemOrderComponent , title: "All orders" ,  canActivate: [AuthGuard] },
  { path: 'myMessages', component: MyMessagesComponent , title: "My messages" ,  canActivate: [AuthGuard] },
  { path: 'feedback', component: AdminContactInfoComponent , title: "All feedback" ,  canActivate: [AuthGuard] },

  { path: 'product/add', component: ProductActionComponent , title: "product" ,  canActivate: [AuthGuard] },
  { path: 'product/edit/:id', component: ProductActionComponent , title: "product" ,  canActivate: [AuthGuard] },



  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: '**', redirectTo: '/products', pathMatch: 'full' }
];

const routerOptions: ExtraOptions = {
  scrollPositionRestoration: 'enabled'
};
