import {inject, Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import {jwtDecode} from 'jwt-decode';
import {ToastrService} from 'ngx-toastr';

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

  private roles: string = '';
  private UserMail : string = '';
  private expiry : number = 0;
  private apiUrl = 'http://localhost:9090/api/login/loginUser';
  toastr = inject(ToastrService);

  setShowNavbar(visible: boolean): void {
    this.showNavbarSource.next(visible);
  }
  constructor(private http: HttpClient, private router: Router) {
    this.loadToken();
  }

  login(email: string, password: string): Observable<AuthResponse | null> {
    ;
    return this.http.post<AuthResponse>(`${this.apiUrl}`, { email, password }, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }).pipe(
      tap(res => {
        if (res.code == 200) {
          localStorage.setItem('token', res.token);
          this.loadToken()
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
    this.toastr.error("logged out!");
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
    this.roles = '';
    this.UserMail='';
  }

  loadToken() {
    ;
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = jwtDecode<any>(token);
      (decodedToken);
      this.roles = decodedToken.role ? decodedToken.role : "USER";
      this.UserMail = decodedToken.sub;
      this.expiry = decodedToken.exp;
    }
  }

  isTokenExpired(): boolean {

    const now = Date.now()/1000;
    return this.expiry < now;
  }

  hasRole(...role: string[]): boolean {
    return role.includes(this.roles);
  }

  hasAnyRole(requiredRoles: string[]): boolean {
    return requiredRoles.some(role => this.roles.includes(role));
  }
  getRole (): any {
    return this.roles;
    // const token = localStorage.getItem('token');
    // if (token) {
    //   const decodedToken = jwtDecode<any>(token);
    //   return decodedToken.role;
    // }
  }
  hasUser(User :string){
    return this.UserMail === User;
  }
  getUser(): string {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = jwtDecode<any>(token);
      return decodedToken.sub;
    }
      return '';
  }
}

