import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  // List of routes that should not receive the Authorization header
  private routesWithoutAuth: string[] = [
    'api/login/loginUser',
    'api/book/getBookById',
    'api/book/getAllBooks',
    'api/reviewAndRating/addReviewAndRating',
    'api/reviewAndRating/toggleLikeToReview',
    'api/reviewAndRating/toggleDisLikeToReview'
  ];

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Check if the request URL is one of the routes that require no auth token
    if (this.shouldSkipAuth(request.url)) {
      return next.handle(request); // Send the request without modifying it
    }

    const authToken = this.authService.getToken();
    if (authToken) {
      // Clone the request and add the Authorization header
      const authReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${authToken}`)
      });
      return next.handle(authReq);
    }

    // If no token and the route needs auth, just forward the request
    return next.handle(request);
  }

  // Determine if the route should skip the authentication token
  private shouldSkipAuth(url: string): boolean {
    return this.routesWithoutAuth.some(route => url.includes(route));
  }
}
