import {Comments} from './comments';

export interface ContactRequest {
  id?: number;

  name: string;

  email: string;

  subject: string;

  message: string;
  userId?: number;

  comments?: Comments[]
}
