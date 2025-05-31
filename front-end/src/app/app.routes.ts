import {ExtraOptions, Routes} from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { CardDetailsComponent } from './components/card-details/card-details.component';
import { ContactInfoComponent } from './components/contact-info/contact-info.component';
import { ChefsComponent } from './components/chefs/chefs.component';



export const routes: Routes = [
  { path: 'products', component: ProductsComponent , title: "Home" },
  {path : 'category/:id', component: ProductsComponent , title: "Category" },
  {path : 'products/search/:key', component: ProductsComponent , title: "search" },
  {path : 'searchInCategory/:id/search/:key', component: ProductsComponent , title: "search" },
  { path: 'cardDetails', component: CardDetailsComponent , title: "Card Details" },
  { path: 'contact-info', component: ContactInfoComponent , title: "Contact Info" },
  { path: 'chefs', component: ChefsComponent , title: "Teams" },
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: '**', redirectTo: '/products', pathMatch: 'full' }
];

const routerOptions: ExtraOptions = {
  scrollPositionRestoration: 'enabled'
};
