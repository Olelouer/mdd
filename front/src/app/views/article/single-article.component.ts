import { Component, DestroyRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Article } from '../../interfaces/article/article.interface';
import { Title } from '@angular/platform-browser';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

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
    MatProgressSpinnerModule
  ],
  templateUrl: './single-article.component.html'
})
export class SingleArticleComponent {
  public article!: Article;
  public isLoading: boolean = true;
  public articleId!: string;

  constructor(
    private articleService: ArticleService,
    private route: ActivatedRoute,
    private destroyRef: DestroyRef,
    private titleService: Title
  ) { }

  ngOnInit(): void {
    const routeID = this.route.snapshot.paramMap.get('id');

    if (routeID !== null) {
      this.articleId = routeID;
      this.loadArticle(this.articleId);
    } else {
      console.error("ID de l'article non présent dans l'URL.");
      this.isLoading = false;
    }
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

  handleNewCommentPosted(): void {
    if (this.articleId) {
      this.loadArticle(this.articleId);
    }
  }
}
