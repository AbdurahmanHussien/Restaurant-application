import {Component, inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {
  NgIf,
  NgOptimizedImage,
  CommonModule,
} from '@angular/common';
import { AuthService } from '../../services/auth.service';

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
    this.route.queryParams.subscribe(params => {
      if (params['loggedOut'] === 'true') {
        this.logOutMessage = 'You have been logged out successfully';
      }
    });
  }

  constructor(private router:Router , private route: ActivatedRoute , private loginService: AuthService) {}
  private fb = inject(FormBuilder);


  errorMessage:String = '';
  showPassword = false;

  logOutMessage = '';


  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  });

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  onSubmit() {
    if (this.loginForm.invalid) return;
    if(localStorage.getItem('jwt_token')){
      this.router.navigate(['/products']);
    }

    this.loginService.login(this.loginForm.value).subscribe({
      next: (res) =>{
        const token = res.token;
        localStorage.setItem('jwt_token', token);
        this.router.navigate(['/products'])},

      error: () => this.errorMessage='Invalid email or password'
    });
  }
}
