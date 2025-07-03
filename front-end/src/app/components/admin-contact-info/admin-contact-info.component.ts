import { Component, OnInit } from '@angular/core';
import {ContactService} from '../../services/contact.service';
import {AuthService} from '../../services/auth.service';
import {CommentService} from '../../services/comment.service';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf} from '@angular/common';
import {TimeagoPipe} from '../../services/timeago.pipe';
import {ToastrService} from 'ngx-toastr';




@Component({
  selector: 'app-admin-contact-info',
  templateUrl: './admin-contact-info.component.html',
  imports: [
    FormsModule,
    NgForOf,
    NgIf,
    TimeagoPipe
  ],
})
export class AdminContactInfoComponent implements OnInit {

  contactList: any = [];
  selectedContact: any = null;
  replyValue: string = '';
  filterType: 'all' | 'replied' | 'notReplied' = 'all';
  errorMessage: string;


  constructor(
    private contactService: ContactService,
    private commentService: CommentService,
    private authService: AuthService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.getAllMessages();
  }

  getAllMessages() {
    this.contactService.getAllContacts().subscribe({
      next: (data) => {
      if (!data ) {
        this.errorMessage = 'you have no messages';
        this.contactList = [];
      }
      this.contactList = data;
    },
      error: (err) => {
        console.error('Error fetching contact messages', err);
        this.errorMessage = 'you have no messages';
      }
    });
  }

  filteredMessages() {
    if (this.filterType === 'replied') {
      return this.contactList.filter(c => c.comments && c.comments.length > 0);
    } else if (this.filterType === 'notReplied') {
      return this.contactList.filter(c => !c.comments || c.comments.length === 0);
    }
    return this.contactList  || [];
  }

  openReplyBox(contact: any) {
    this.selectedContact = contact;
    this.replyValue = '';
  }

  submitReply(contactId: number) {
    const comment = {
      value: this.replyValue,
      contactInfoId: contactId,
      userId: this.authService.getCurrentUser(),
    };

    this.commentService.addComment(comment).subscribe({
      next: () => {
        this.replyValue = '';
        this.selectedContact = null;
        this.getAllMessages();
        this.toastr.success('your comment has been sent', 'success')
      },
      error: () => {
        this.toastr.error('your comment is too short', 'failed')
      }
    });
  }


  deleteComment(id: number){
    this.commentService.deleteComment(id).subscribe(() => {
      this.getAllMessages();
      this.toastr.success('comment has been deleted', 'success')
    });
  }
}
