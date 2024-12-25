import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponseModel} from '../Model/Interface/Book';
import {Staff, UpdateStaffDetails, UpdateUserDetails, UserDetails} from '../Model/Interface/MyDetails';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {
  private userUrl = 'http://localhost:9090/api/user';
  private staffUrl = 'http://localhost:9090/api/staff';
  http : HttpClient = inject(HttpClient);

  constructor() { }

  getStaffDetails(id :string):Observable<ApiResponseModel> {
    (id);
    return this.http.get<ApiResponseModel>(`${this.staffUrl}/getStaffById/${id}`);
  }

  getUserDetails(id: string) {
    return this.http.get<ApiResponseModel>(`${this.userUrl}/getUserById/${id}`);
  }
  updateStaffDetails(id: number, staff: UpdateStaffDetails): Observable<null> {
    debugger;
    return this.http.put<null>(`${this.staffUrl}/updateStaff/${id}`, staff);
  }

  updateUserDetails(id: number, user: UpdateUserDetails): Observable<null> {
    debugger;
    return this.http.put<null>(`${this.userUrl}/updateUser/${id}`, user);
  }
}
