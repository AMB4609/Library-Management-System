import { Routes } from '@angular/router';
import { LoginComponent } from './Components/login/login.component';
import { BookComponent } from './Components/book/book.component';
import { BookDetailsComponent } from './Components/book-details/book-details.component';
import { AddBookComponent } from './Components/add-book/add-book.component';
import {roleGuard} from './Guard/role.guard';
import {RegisterComponent} from './Components/register/register.component';
import {MainLayoutComponent} from './Components/main-layout/main-layout.component';
import {BranchComponent} from './Components/branch/branch.component';
import {AddEditBranchComponent} from './Components/add-edit-branch/add-edit-branch.component';
import {BranchDetailComponent} from './Components/branch-details/branch-details.component';
import {StaffUserDetailsComponent} from './Components/staff-user-details/staff-user-details.component';
import {LandingComponent} from './Components/landing/landing.component';
import {EditStaffUserDetailsComponent} from './Components/edit-staff-user-details/edit-staff-user-details.component';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      {
        path: '',
        component: LandingComponent,
        pathMatch: 'full',
      },
      {
        path: 'book',
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
        redirectTo: 'book',
      },
      {
        path: 'books/:id',  // URL for viewing book details
        component: BookDetailsComponent,
      },
      {
        path: 'add',  // URL for adding a new book
        component: AddBookComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN','LIBRARIAN'] }
      },
      {
        path: 'edit/:id',  // URL for editing an existing book
        component: AddBookComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN','LIBRARIAN'] }
      },
      {
        path: 'register',
        component: RegisterComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN','LIBRARIAN']}
      },
      {
        path : 'branch',
        component: BranchComponent,

      },
      {
        path: 'addBranch',
        component: AddEditBranchComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN','LIBRARIAN']}
      },
      {
        path: 'editBranch/:id',
        component: AddEditBranchComponent,
        canActivate: [roleGuard],
        data: { role: ['ADMIN','LIBRARIAN']}
      },
      {
        path: 'branches/:id',
        component: BranchDetailComponent,
      },
      {
        path: 'MyDetails',
        component : StaffUserDetailsComponent,
      },
      {
        path:'update-staff-user',
        component: EditStaffUserDetailsComponent
      }

    ]
  }
];
