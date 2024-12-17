import { Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { BookComponent } from './Components/book/book.component';
import { BookDetailsComponent } from './Components/book-details/book-details.component';
import { AddBookComponent } from './Components/add-book/add-book.component';
import {RoleGuard} from './Guard/role-guard.guard';
import {RegisterComponent} from './Components/register/register.component';
import {MainLayoutComponent} from './Components/main-layout/main-layout.component';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: '',
        component: BookComponent,  // Directs root path to LoginComponent
        pathMatch: 'full'
      },
      {
        path: 'login',
        component: LoginComponent,  // Redirect '/login' to the root path
        pathMatch: 'full'
      },
      {
        path: 'books',  // Changed from 'getAll' to 'books' for clarity and convention
        redirectTo: '',
      },
      {
        path: 'books/:id',  // URL for viewing book details
        component: BookDetailsComponent,
      },
      {
        path: 'add',  // URL for adding a new book
        component: AddBookComponent,
        canActivate: [RoleGuard],
        data: { role: ['ADMIN'] }
      },
      {
        path: 'edit/:id',  // URL for editing an existing book
        component: AddBookComponent,
        canActivate: [RoleGuard],
        data: { role: ['ADMIN'] }
      },
      {
        path: 'register',
        component: RegisterComponent,
        canActivate: [RoleGuard],
        data: { role: ['ADMIN','LIBRARIAN']}
      }
    ]
  }
];
