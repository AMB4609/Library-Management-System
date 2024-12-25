import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import { AuthService } from '../../Service/auth.service'
import {DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit,OnDestroy {
  email: string = '';
  password: string = '';


authService = inject(AuthService);
toastr = inject(ToastrService);
router = inject(Router);
  ngOnInit(): void {
    this.authService.setShowNavbar(false);
    this.authService.logout()// Hide navbar on login page
  }

  ngOnDestroy(): void {
    this.authService.setShowNavbar(true);   // Show navbar when leaving the login page
  }
  onLogin(): void {
    this.authService.login(this.email, this.password).subscribe(success => {
      if (success) {
        this.toastr.success('Login Successful!');
        if(this.authService.hasRole("ADMIN")){
          console.log("Admin logged in");
        }else if(this.authService.hasRole("LIBRARIAN")) {
          console.log("librarian logged in");
        }else {
          console.log("User logged in");
        }
        this.router.navigate(['/books']);
      } else {
        this.toastr.error('Login Failed! why are you clicking', "Error");
      }
    });
  }

}
