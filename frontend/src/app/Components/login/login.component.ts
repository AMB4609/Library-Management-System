import {Component, inject, OnInit} from '@angular/core';
import { AuthService } from '../../Service/auth.service'
import {DatePipe} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';


authService = inject(AuthService);
router = inject(Router);
  ngOnInit(): void {
  }
  onLogin(): void {
    debugger;
    this.authService.login(this.email, this.password).subscribe(success => {
      if (success) {
        alert('Login Successful!');
        this.router.navigate(['/book']);
      } else {
        alert('Login Failed! why are you clicking');
      }
    });
  }

}
