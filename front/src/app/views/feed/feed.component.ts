import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { finalize, Observable } from 'rxjs';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../interfaces/article/article.interface';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { Title } from '@angular/platform-browser';
import { HeaderComponent } from '../../components/header/header.component';
import { ArticleCardComponent } from '../../components/article-card/article-card.component';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-feed',
  imports: [
    CommonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatIconModule,
    HeaderComponent,
    ArticleCardComponent,
    MatButtonModule
  ],
  templateUrl: './feed.component.html',
})
export class FeedComponent {
  public articles$!: Observable<Article[]>;
  public currentSortOrder: 'asc' | 'desc' = 'asc';
  public isLoading: boolean = false;

  constructor(private articleService: ArticleService, private titleService: Title) {
  }

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
