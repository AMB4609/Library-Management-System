import { Component, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService} from '../../Service/auth.service';
import {UserDetailsService } from '../../Service/user-details.service';
import { ToastrService } from 'ngx-toastr';
import {NgIf} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-update-details',
  templateUrl: './edit-staff-user-details.component.html',
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  styleUrls: ['./edit-staff-user-details.component.css']
})
export class EditStaffUserDetailsComponent implements OnInit {
  updateUserForm: FormGroup;
  updateStaffForm: FormGroup;
  role: string | undefined;
  id: string = '';
  updateId: number = 0;
  router = inject(Router);

  constructor(
    private fb: FormBuilder,
    private authService: AuthService = inject(AuthService),
    private userDetailsService: UserDetailsService = inject(UserDetailsService),
    private toastr: ToastrService = inject(ToastrService),
  ) {
    this.updateUserForm = this.fb.group({
      userName: ['', Validators.required],
      userEmail: ['', Validators.required],
      userPhone: ['', Validators.required],
      userAddress: ['', Validators.required],
      userPassword: ['', Validators.required]
    });
    this.updateStaffForm = this.fb.group({
      staffName: ['', Validators.required],
      staffEmail: ['', Validators.required],
      staffPhone: ['', Validators.required],
      staffAddress: ['', Validators.required],
      staffPassword: ['', Validators.required]
    })
  }

  ngOnInit() {
    this.role = this.authService.getRole();
    this.id = this.authService.getUser();
    this.loadInitialDetails();
  }

  private loadInitialDetails() {
    debugger;
    if (this.role === 'ADMIN' || this.role === 'LIBRARIAN') {
      debugger;
      this.userDetailsService.getStaffDetails(this.id).subscribe({
        next: response => {
          this.updateStaffForm.patchValue(response.data)
          this.updateId = response.data.staffId;
        }
        ,
        error: err => this.toastr.error('Error loading details')
      });
    } else if (this.role === 'USER') {
      debugger;
      this.userDetailsService.getUserDetails(this.id).subscribe({
        next: response =>{
          this.updateUserForm.patchValue(response.data)
          this.updateId = response.data.userId;
        },
        error: err => this.toastr.error('Error loading details')
      });
    }
  }

  updateDetails() {
    if (this.role === 'ADMIN' || this.role === 'LIBRARIAN') {
      debugger;
      this.userDetailsService.updateStaffDetails(this.updateId, this.updateStaffForm.value).subscribe({
        next: () => {
          this.toastr.success('Staff updated successfully');
          this.router.navigate(['MyDetails']);
        } ,
        error: err => this.toastr.error('Update failed')
      });
    } else if (this.role === 'USER') {
      debugger;
      this.userDetailsService.updateUserDetails(this.updateId, this.updateUserForm.value).subscribe({
        next: () => {
          this.toastr.success('User updated successfully')
          this.router.navigate(['MyDetails']);
        },
        error: err => this.toastr.error('Update failed')
      });
    }
  }
}
