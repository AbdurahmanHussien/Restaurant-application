import {Component, OnInit} from '@angular/core';
import {ContactService} from '../../services/contact.service';
import {ContactRequest} from '../../models/contactRequest';
import { NgForOf, NgIf} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CommentService} from '../../services/comment.service';
import {AuthService} from '../../services/auth.service';
import {TimeagoPipe} from '../../services/timeago.pipe';

@Component({
  selector: 'app-my-messages',
  imports: [
    NgForOf,
    NgIf,
    FormsModule,
    TimeagoPipe
  ],
  templateUrl: './my-messages.component.html',
  styleUrl: './my-messages.component.css'
})
export class MyMessagesComponent implements  OnInit {
  constructor(private _contactService: ContactService ,
              private commentService: CommentService, private authService: AuthService) { }

  contactList: ContactRequest[] = [];

  errorMessage: string;


  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUserName();
    this.getAllContactMessages();
    console.log(this.contactList);

    }

  getAllContactMessages() {
    this._contactService.getMessages().subscribe({
      next: (data) => {
        if (data.length === 0) {
          this.errorMessage = 'you have no messages';
          this.contactList = [];
        }else {

          this.contactList = data;
        }
      },
      error: (err) => {
        console.error('Error fetching contact messages', err);
        this.errorMessage = 'you have no messages';
      }
    });
  }

  selectedContact: ContactRequest | null = null;
  replyValue: string = '';
  currentUser: string ;

  openReplyBox(contact: ContactRequest) {
    this.selectedContact = contact;
    this.replyValue = '';
  }

  submitReply(contactId: number) {
   // debugger

    const comment = {
      value: this.replyValue,
      contactInfoId: contactId,
      userId: this.authService.getCurrentUser(),
    };


    this.commentService.addComment(comment).subscribe(() => {
      this.replyValue = '';
      this.selectedContact = null;
      this.getAllContactMessages();
    });
  }
  deleteComment(id: number){
    this.commentService.deleteComment(id).subscribe(() => {
      this.getAllContactMessages();
    });
  }
}
