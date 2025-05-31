import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ContactService} from '../../services/contact.service';
import {ContactRequest} from '../../models/contactRequest';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-contact-info',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.css']
})
export class ContactInfoComponent implements OnInit {

  constructor( private _contactService: ContactService) {}

  ngOnInit(): void {}


  contactData: ContactRequest = {
    name: '',
    email: '',
    subject: '',
    message: ''
  };

  onSubmit() {
    this._contactService.sendMessage(this.contactData).subscribe({
      next: () => {
        alert('your message has been sent');
      },
      error: () => {
        alert('error while sending message');
      }
    });
  }

}
