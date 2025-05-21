import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Article } from '../../../interfaces/article/article.interface';

@Component({
  selector: 'cpn-article-card',
  imports: [CommonModule, RouterLink],
  templateUrl: './article-card.component.html'
})
export class ArticleCardComponent {
  @Input() article?: Article;
}
