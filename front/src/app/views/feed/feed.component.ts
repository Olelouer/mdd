import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { finalize, Observable } from 'rxjs';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../interfaces/article/article.interface';
import { Title } from '@angular/platform-browser';
import { HeaderComponent } from '../../components/header/header.component';
import { ArticleCardComponent } from '../../components/cards/article-card/article-card.component';

import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-feed',
  imports: [
    CommonModule,
    HeaderComponent,
    ArticleCardComponent,
    MatProgressSpinnerModule,
    MatButtonModule
  ],
  templateUrl: './feed.component.html',
})
export class FeedComponent {
  public articles$!: Observable<Article[]>;
  public currentSortOrder: 'asc' | 'desc' = 'asc';
  public isLoading: boolean = false;

  constructor(
    private articleService: ArticleService,
    private titleService: Title
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle("Fil d'actualité - MDD")
    this.loadArticles();
  }

  loadArticles(): void {
    this.isLoading = true;
    this.articles$ = this.articleService.getUserFeed(this.currentSortOrder).pipe(
      finalize(() => {
        this.isLoading = false;
      })
    )
  }

  switchSort(): void {
    this.currentSortOrder = this.currentSortOrder === 'asc' ? 'desc' : 'asc';
    this.loadArticles();
  }
}
