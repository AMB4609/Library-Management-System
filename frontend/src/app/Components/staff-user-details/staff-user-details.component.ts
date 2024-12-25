import {Component, inject, OnInit} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {AuthService} from '../../Service/auth.service';
import {Staff, UserDetails} from '../../Model/Interface/MyDetails';
import {UserDetailsService} from '../../Service/user-details.service';
import {ApiResponseModel, User} from '../../Model/Interface/Book';
import {ToastrService} from 'ngx-toastr';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-staff-user-details',
  imports: [ReactiveFormsModule, NgIf, RouterLink],
  templateUrl: './staff-user-details.component.html',
  styleUrl: './staff-user-details.component.css'
})
export class StaffUserDetailsComponent implements OnInit {
  role: string | undefined;
  authService : AuthService = inject(AuthService);
  userDetailsService: UserDetailsService = inject(UserDetailsService);
  staff: Staff | null = null;
  user : UserDetails | null = null;
  id : string | undefined;
  toastr = inject(ToastrService);

  ngOnInit() {
    this.role = this.authService.getRole();
    this.viewDetails();
  }

  private viewDetails() {
    this.id = this.authService.getUser();
    if (this.role == 'ADMIN' || this.role == 'LIBRARIAN') {
      this.userDetailsService.getStaffDetails(this.id).subscribe({
        next: (response: ApiResponseModel) => {
          this.staff = response.data;
        },
        error: (error : any) => {
          this.toastr.error('Error fetching staff details:', error);
        }
      })
    }else if (this.role == 'USER') {
      this.userDetailsService.getUserDetails(this.id).subscribe({
        next: (response: ApiResponseModel) => {
          this.user = response.data;
        },
        error: (error : any) => {
          this.toastr.error('Error fetching user details:', error);
        }
      })
    }
  }
}
