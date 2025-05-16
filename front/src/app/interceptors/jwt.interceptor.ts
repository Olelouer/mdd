import { HttpEvent, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';

export const jwtInterceptor: HttpInterceptorFn = (req, next): Observable<HttpEvent<unknown>> => {
  const authService = inject(AuthService);
  const token = authService.getToken();
  const isLoggedIn = authService.isLoggedIn();

  if (isLoggedIn) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })
    return next(clonedRequest)
  }

  return next(req);
};
