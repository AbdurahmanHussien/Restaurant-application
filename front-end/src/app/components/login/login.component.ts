import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {
  FormBuilder, FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {
  NgIf,
  NgOptimizedImage,
  CommonModule,
} from '@angular/common';
import { AuthService } from '../../services/auth.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    NgIf,
    NgOptimizedImage,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });

    this.route.queryParams.subscribe(params => {
      if (params['loggedOut'] === 'true') {
        this.logOutMessage = 'You have been logged out successfully';
      }
    });
  }

  constructor(private router: Router,
              private route: ActivatedRoute,
              private loginService: AuthService,
              private toastr: ToastrService) {
  }

  private fb = inject(FormBuilder);


  errorMessage: String = '';
  showPassword = false;
  form: FormGroup;

  logOutMessage = '';
  UserRole: any;


  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    if (this.form.invalid) return;

    const formValue = this.form.value;

    const payload = {
      ...formValue,
      email: formValue.email.trim().toLowerCase()
    };

    if (localStorage.getItem('jwt_token')) {
      this.router.navigate(['/products']);
    }

    this.loginService.login(payload).subscribe({
      next: (res) => {
        const token = res.token;
        const userId = res.userId;
        console.log(userId);
        localStorage.setItem('jwt_token', token);
        this.UserRole = res.userRole;
        localStorage.setItem('roles', this.UserRole);
        localStorage.setItem('userId', userId);
        console.log(this.UserRole);
        this.toastr.success('You have been logged in successfully', 'Welcome')
        this.router.navigate(['/products'])
      },

      error: () => this.errorMessage = 'Invalid email or password'
    });
  }


  forgetPassword() {
    this.loginService.resetPassword({ email: this.form.value.email }).subscribe({
      next: () =>
        alert('Password reset to Hello@1234'),
      error: () =>
        alert('cannot reset password')
    });
  }
}
