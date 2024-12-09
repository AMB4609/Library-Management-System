import { Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import {BookComponent} from './Components/book/book.component';
import {BookDetailsComponent} from './Components/book-details/book-details.component';

export const routes: Routes = [
  {
    path: '',
    component: LoginComponent,  // Direct root path to LoginComponent
    pathMatch: 'full'
  },
  // Optionally, you can keep this if you might add other routes later
  {
    path: 'login',
    redirectTo: '',  // Redirect '/login' to the root path
    pathMatch: 'full'
  },
  {
    path: 'book',
    component: BookComponent,
  },
  {
    path: 'book/:id',
    component: BookDetailsComponent,
  }
  // {path: 'login', component: LoginComponent},
  // Additional routes can be added here
];
