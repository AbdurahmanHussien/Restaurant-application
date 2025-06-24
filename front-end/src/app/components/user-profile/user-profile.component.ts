import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ToastrService} from 'ngx-toastr';
import {UserData} from '../../models/userData';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-user-profile',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserService, private toastr: ToastrService) {}

  editMode = false;


  originalUserData: UserData = {
    username: '',
    name: '',
    email: '',
    phone: '',
    address: '',
    age: 0
  };

  userData: UserData = {
    username: '',
    name: '',
    email: '',
    phone: '',
    address: '',
    age: 0
  }

  profileErrors : any = {}



  ngOnInit() {
    // @ts-ignore
    this.userData.id = localStorage.getItem('userId');
    this.userService.getUserData(this.userData.id).subscribe({
      next: (res) => {
      this.userData = res;
      this.originalUserData = {...this.userData};
    },
    error: () => {
      this.toastr.error('Error fetching user data', 'Error');
  }
  });
  }

  saveUserData(){
    this.userService.updateUserData(this.userData.id, this.userData).subscribe({
      next: () => {
      this.editMode = false;
      this.toastr.success('your profile has been updated', 'Success');
    },
    error: (err) => {

      this.profileErrors = {};
      err.error.forEach((err: any) => {

        this.profileErrors[err.field] = err.messages.message_en;
      });
      this.toastr.error('Error updating user data', 'Error');
  }
  });
}
  cancelEdit(){
    this.userData = {...this.originalUserData};
    this.editMode = false;
  }
}
