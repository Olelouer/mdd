import { Component, DestroyRef, input, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { CommentService } from '../../../services/comment.service';
import { GlobalMessageResponse } from '../../../interfaces/globalMessageResponse.interface';

@Component({
  selector: 'cpn-comment-form',
  imports: [
    FormsModule
  ],
  templateUrl: './comment-form.component.html'
})
export class CommentFormComponent {
  articleId = input.required<string>();
  commentFormData = {
    commentContent: ''
  }

  @Output() newCommentPosted = new EventEmitter<void>();
  constructor(
    private commentService: CommentService,
    private destroyRef: DestroyRef
  ) { }


  onSubmit(): void {
    const payload = {
      content: this.commentFormData.commentContent,
      articleId: this.articleId()
    };

    this.commentService.postComment(payload)
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (response: GlobalMessageResponse) => {
          this.commentFormData.commentContent = '';
          this.newCommentPosted.emit();
        },
        error: (error: Error) => console.error(error)
      })
  }

}
