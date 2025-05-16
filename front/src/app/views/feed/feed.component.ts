import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { ArticleService } from '../../services/article.service';
import { Article } from '../../interfaces/article/article.interface';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { Title } from '@angular/platform-browser';
import { HeaderComponent } from '../../components/header/header.component';

@Component({
  selector: 'app-feed',
  imports: [
    CommonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatIconModule,
    HeaderComponent
  ],
  templateUrl: './feed.component.html',
})
export class FeedComponent {

  public articles$: Observable<Article[]>;

  constructor(private articleService: ArticleService, private titleService: Title) {
    this.articles$ = this.articleService.getUserFeed();
  }

  ngOnInit(): void {
    this.titleService.setTitle("Fil d'actualité - MDD")
  }
}
