import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { ReactiveFormsModule } from '@angular/forms';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {loaderInterceptor} from './interceptors/loader.interceptor';
import {JwtInterceptor} from './interceptors/jwt.Interceptor';
import {provideToastr} from 'ngx-toastr';


export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideAnimations(),
    importProvidersFrom(ReactiveFormsModule),
    importProvidersFrom(FormsModule),
    provideHttpClient(withInterceptors([loaderInterceptor , JwtInterceptor])),
    provideToastr({
      positionClass: 'toast-top-right',
      timeOut: 5000,
      preventDuplicates: true,
      }),
  ]
};
