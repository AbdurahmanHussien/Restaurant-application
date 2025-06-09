import {Component, inject, OnInit, signal} from '@angular/core';
import {NgIf, NgOptimizedImage} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {FormBuilder, ReactiveFormsModule} from '@angular/forms';

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

  showPassword = false

  signupForm = this.fb.group({
    name: [''],
    username: [''],
    phoneNum: [''],
    age: [''],
    email: [''],
    password: [''],
    address: [''],
  });

  signupErrors : any = {}

  errorMessageEmail: string = ''


  onSubmit() {
    if (this.signupForm.invalid) return;

    this.authService.signup(this.signupForm.value).subscribe({
      next: (res) => {
        this.router.navigate(['/login']);
      },
      error: (err) => {
       // debugger
        this.errorMessageEmail = err.error?.messages?.message_en || '' ;

        this.signupErrors = {};
        err.error.forEach((err: any) => {

          this.signupErrors[err.field] = err.messages.message_en;
        });

      }
    });
  }

  togglePassword() {
    this.showPassword = !this.showPassword
  }


}
