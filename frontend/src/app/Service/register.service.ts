import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private registerUrl = 'http://localhost:9090/api/register'
  http : HttpClient = inject(HttpClient);
  constructor() { }
  registerUser(formValue : any) : Observable<null> {
    return this.http.post<null>(`${this.registerUrl}/registerUser`,formValue);
  }
  registerStaff(formValue : any) : Observable<null> {
    ;
    (formValue);
    return this.http.post<null>(`${this.registerUrl}/registerStaff`,formValue);
  }
}
