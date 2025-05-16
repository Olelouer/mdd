import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Article } from '../../interfaces/article/article.interface';

@Component({
  selector: 'cpn-article-card',
  imports: [CommonModule],
  templateUrl: './article-card.component.html'
})
export class ArticleCardComponent {
  @Input() article?: Article;
}
