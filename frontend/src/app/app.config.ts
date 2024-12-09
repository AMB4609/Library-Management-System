import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app.routes';

// Importing the AuthService
import { AuthService } from './Service/auth.service';

// Importing the AuthInterceptor
import { AuthInterceptor } from './Service/auth-interceptor';

// Configure the HTTP interceptors
import { HTTP_INTERCEPTORS } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),

    // Providing AuthService as a singleton service across the app
    AuthService,

    // Providing AuthInterceptor to intercept all HTTP requests
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
};
