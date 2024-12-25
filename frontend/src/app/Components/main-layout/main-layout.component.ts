import {Component, inject} from '@angular/core';
import {NavigationEnd, Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {AuthService} from '../../Service/auth.service';
import {AsyncPipe, NgIf} from '@angular/common';
import {filter} from 'rxjs';

@Component({
  selector: 'app-main-layout',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, NgIf, AsyncPipe],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent {
  authService: AuthService = inject(AuthService);
  showNavbar$ = this.authService.showNavbar;
  showNavbar = false
  title = 'LibraryManagementSystem';

  constructor(private router: Router) {
    this.showNavbar = !this.router.url.includes('/login');  // Initialize based on initial URL
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.showNavbar = !['/login'].includes(event.urlAfterRedirects);
    });
  }

  onActivate(event: any) {
    window.scroll(0, 0);  // Optional: Scrolls to top on route change
  }
}
