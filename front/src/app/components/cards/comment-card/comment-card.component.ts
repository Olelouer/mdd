import { Component, input } from '@angular/core';
import { Comment } from '../../../interfaces/comment/comment.interface';

@Component({
  selector: 'cpn-comment-card',
  imports: [],
  templateUrl: './comment-card.component.html',
})
export class CommentCardComponent {
  comment = input.required<Comment>();

}
