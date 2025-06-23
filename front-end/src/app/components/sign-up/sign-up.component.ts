import {Component, inject, OnInit} from '@angular/core';
import {NgIf, NgOptimizedImage} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';

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

  constructor(private router: Router , private authService: AuthService,  private toastr: ToastrService) {}
  private fb = inject(FormBuilder);


  form : FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [''],
      username: [''],
      phoneNum: [''],
      age: [''],
      email: [''],
      password: [''],
      address: [''],
    });
    this.form.get('email')?.valueChanges.subscribe(value => {
      if (value) {
        const clean = value.trim().toLowerCase();
        if (clean !== value) {
          this.form.get('email')?.setValue(clean, { emitEvent: false });
        }
      }
    });
  }

  showPassword = false
  UserRole: any  ;


  signupErrors : any = {}

  errorMessageEmail: string = ''


  onSubmit() {
    if (this.form.invalid) return;

    const formValue = this.form.value;

    const payload = {
      ...formValue,
      email: formValue.email.trim().toLowerCase()
    };

    this.authService.signup(payload).subscribe({
      next: (res) => {
        const token = res.token;
        const userId = res.userId;
        localStorage.setItem('jwt_token', token);
        this.UserRole = res.userRole;
        localStorage.setItem('roles', this.UserRole);
        localStorage.setItem('userId', userId);

        console.log( this.UserRole)
        this.toastr.success('You have been signed up in successfully', 'Welcome')
        this.router.navigate(['/products'])
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
