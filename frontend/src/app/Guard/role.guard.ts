import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../Service/auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const router :Router = inject(Router);
  const authService = inject(AuthService);
  const requiredRoles : string[] = route.data['role'];
  if (authService.isTokenExpired()) {
    router.navigate(['/login']);
    return false;
  }
  const userRole = authService.getRole();
  (userRole);
  if (requiredRoles && requiredRoles.includes(userRole)) {
    return true;
  }
  router.navigate(['/']);
  return false;
};
