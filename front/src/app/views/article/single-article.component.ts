import { Component, DestroyRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Article } from '../../interfaces/article/article.interface';
import { Title } from '@angular/platform-browser';

import { HeaderComponent } from '../../components/header/header.component';
import { BackArrowComponent } from '../../components/back-arrow/back-arrow.component';
import { CommentCardComponent } from '../../components/cards/comment-card/comment-card.component';
import { CommentFormComponent } from '../../components/forms/comment-form/comment-form.component';

@Component({
  selector: 'app-single-article',
  imports: [
    CommonModule,
    CommentFormComponent,
    HeaderComponent,
    BackArrowComponent,
    CommentCardComponent,
  ],
  templateUrl: './single-article.component.html'
})
export class SingleArticleComponent {
  public article!: Article;
  public isLoading: boolean = true;

  constructor(
    private articleService: ArticleService,
    private route: ActivatedRoute,
    private destroyRef: DestroyRef,
    private titleService: Title
  ) { }

  ngOnInit(): void {
    const articleId = this.route.snapshot.paramMap.get('id');

    if (articleId) this.loadArticle(articleId);
  }

  loadArticle(articleId: string) {
    this.isLoading = true;
    this.articleService.getArticle(articleId)
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (data: Article) => {
          this.article = data;
          this.isLoading = false;
          this.titleService.setTitle(`${this.article.title} - MDD`);
        },
        error: (err) => {
          console.error(err);
          this.isLoading = false;
        }
      })
  }
}
