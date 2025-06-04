import {Component, inject, OnInit, signal} from '@angular/core';
import {NgIf, NgOptimizedImage} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  imports: [
    NgIf,
    RouterLink,
    NgOptimizedImage,
    ReactiveFormsModule
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent implements OnInit {

  constructor(private router: Router , private authService: AuthService) {}
  private fb = inject(FormBuilder);


  ngOnInit(): void {
    }


  errorMessage = signal('');
  successMessage = signal('');
  showPassword = false

  signupForm = this.fb.group({
    name: ['', Validators.required],
    username: ['', Validators.required],
    phoneNum: ['', [Validators.required, Validators.pattern(/^\d{10,15}$/)]], // رقم موبايل بين 10 و15 رقم
    age: ['', [Validators.required, Validators.min(1), Validators.max(120)]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    address: ['', Validators.required],
  });

  onSubmit() {
    if (this.signupForm.invalid) return;

    this.authService.signup(this.signupForm.value).subscribe({
      next: (res) => {
        this.successMessage.set('Registration successful. You can login now.');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.errorMessage.set('Registration failed. Try again.');
      }
    });
  }

  togglePassword() {
    this.showPassword = !this.showPassword
  }


}
