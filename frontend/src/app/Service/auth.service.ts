import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import {jwtDecode} from 'jwt-decode';

export interface AuthResponse {
  token: string;
  code : number;
  message: string;
  status: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private showNavbarSource = new BehaviorSubject<boolean>(true);
  showNavbar = this.showNavbarSource.asObservable();

  private roles: Array<string> = [];
  private UserMail : string = '';
  private apiUrl = 'http://localhost:9090/api/login/loginUser'; // Adjust your API URL here

  setShowNavbar(visible: boolean): void {
    this.showNavbarSource.next(visible);
  }
  constructor(private http: HttpClient, private router: Router) {
    this.loadToken()
  }

  login(email: string, password: string): Observable<AuthResponse | null> {
    debugger;
    return this.http.post<AuthResponse>(`${this.apiUrl}`, { email, password }, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }).pipe(
      tap(res => {
        if (res.code == 200) {
          localStorage.setItem('token', res.token);
        }
      }),
      catchError(error => {
        console.error('Login error:', error);
        return of(null);
      })
    );
  }

  hasToken():boolean {
    return localStorage.getItem('token') == null;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  loadToken() {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = jwtDecode<any>(token);
      console.log(decodedToken);
      this.roles = decodedToken.role ? [decodedToken.role] : ["USER"];
      console.log(this.roles);
      this.UserMail = decodedToken.sub;
      console.log(this.UserMail);
    }
  }

  hasRole(role: string): boolean {
    return this.roles.includes(role);
  }

  hasAnyRole(requiredRoles: string[]): boolean {
    return requiredRoles.some(role => this.roles.includes(role));
  }

  hasUser(User :string){
    debugger;
    return this.UserMail.includes(User);
  }
}

