import { Component, DestroyRef, Input, inject } from '@angular/core';
import { ArticleService } from '../../services/article.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'cpn-like-button',
  imports: [],
  templateUrl: './like-button.component.html'
})
export class LikeButtonComponent {
  @Input() likeCount?: number;
  @Input() itemId?: number;
  @Input() liked?: boolean;
  private articleService = inject(ArticleService);
  private destroyRef = inject(DestroyRef);

  likeToggle() {
    this.articleService.likeArticle(String(this.itemId))
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe();
  }
}
