import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {NgIf} from '@angular/common';
import {RegisterService} from '../../Service/register.service';
import {ToastrService} from 'ngx-toastr';
import {AuthService} from '../../Service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  userForm: FormGroup;
  staffForm: FormGroup;
  isUserFormActive: boolean = true;
  registerService: RegisterService = inject(RegisterService);
  toastr = inject(ToastrService);
  authService = inject(AuthService);

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {
    // User Form Initialization
    this.userForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(2)]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      phone: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      address: new FormControl('', Validators.required),
    });

    // Staff Form Initialization with an additional 'position' field
    this.staffForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(2)]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      phone: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      address: new FormControl('', Validators.required),
      branchId: new FormControl('', Validators.required),
      position: new FormControl('', Validators.required) // Additional field for staff
    });
  }

  onSubmitUser(): void {
    if (this.userForm.valid) {
      this.registerService.registerUser(this.userForm.value).subscribe({
        next: (response) => {
          this.toastr.success("Register success");
          this.router.navigate(['/register']);
        },
        error: (error) => {
          this.toastr.error('Registration failed:', error);
        }
      });
    }else {
      this.toastr.success("Register failed");
    }
  }

  onSubmitStaff(): void {
    if (this.staffForm.valid) {
      this.registerService.registerStaff(this.staffForm.value).subscribe({
        next: (response) => {
          this.toastr.success("Register success");
          this.router.navigate(['/register']);
        },
        error: (error) => {
          this.toastr.error('Registration failed:', error);
        }
      });
    }
    else {
      this.toastr.error("Register failed");
    }
  }

  toggleForm() {
    this.isUserFormActive = !this.isUserFormActive;
  }
}
