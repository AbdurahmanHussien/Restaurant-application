import {HttpClient, HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {catchError, switchMap, throwError} from 'rxjs';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('jwt_token');
  const refreshToken = localStorage.getItem('refresh_token');

  const router = inject(Router);
  const toastr = inject(ToastrService);
  const http = inject(HttpClient);

  const authReq = token
    ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } })
    : req;

  return next(authReq).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401 && !req.url.includes('login') && refreshToken) {
        return http.post<{ accessToken: string }>('http://localhost:8080/auth/refresh-token', {
          refreshToken: refreshToken
        }).pipe(
          switchMap(response => {
            localStorage.setItem('jwt_token', response.accessToken);

            const retryReq = req.clone({
              setHeaders: { Authorization: `Bearer ${response.accessToken}` }
            });

            return next(retryReq);
          }),
          catchError(refreshError => {
            localStorage.removeItem('jwt_token');
            localStorage.removeItem('refresh_token');
            toastr.error('Session expired. Please login again.', 'Unauthorized');
            router.navigate(['/login']);
            return throwError(() => refreshError);
          })
        );
      }

      if (error.status === 401 && req.url.includes('login')) {
        toastr.error('Invalid email or password', 'Unauthorized');
        router.navigate(['/login']);
      }

      return throwError(() => error);
    })
  );
};
