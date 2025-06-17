import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {catchError, throwError} from 'rxjs';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('jwt_token');
  const router = inject(Router);
  const toastr= inject(ToastrService);
  const cloned = token
    ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } })
    : req;

  return next(cloned).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        if(req.url.includes('login')){
          toastr.error('Invalid email or password', 'Unauthorized');
          router.navigate(['/login']);
        } else{
        localStorage.removeItem('jwt_token');
        toastr.error('Session expired. Please login again.', 'Unauthorized');
        router.navigate(['/login']);
      }}
      return throwError(() => error);
    })
  );
};
