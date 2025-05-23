import { Component, DestroyRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Title } from '@angular/platform-browser';
import { FormsModule, NgForm } from '@angular/forms';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { HeaderComponent } from '../../components/header/header.component';
import { BackArrowComponent } from '../../components/back-arrow/back-arrow.component';
import { ArticleResquest } from '../../interfaces/article/articleRequest.interface';

import { ThemeService } from '../../services/theme.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Theme } from '../../interfaces/theme/theme.interface';
import { ArticleService } from '../../services/article.service';
import { GlobalMessageResponse } from '../../interfaces/globalMessageResponse.interface';

@Component({
  selector: 'app-create-article',
  imports: [
    HeaderComponent,
    BackArrowComponent,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatIconModule,
    CommonModule,
    FormsModule
  ],
  templateUrl: './create-article.component.html',
})
export class CreateArticleComponent {
  @ViewChild('articleForm') articleForm!: NgForm;

  articleData: ArticleResquest = {
    title: '',
    content: '',
    themeId: ''
  }

  public globalMessageResponse: string = '';

  public themes!: Theme[];

  constructor(
    private titleService: Title,
    private themeService: ThemeService,
    private articleService: ArticleService,
    private destroyRef: DestroyRef
  ) {
  }

  ngOnInit(): void {
    this.titleService.setTitle("Créer un article - MDD")

    this.loadThemes();
  }

  loadThemes(): void {
    this.themeService.getAllThemes()
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (response: Theme[]) => {
          this.themes = response;
        },
        error: (error: Error) => { console.error(error) }
      })
  }

  onSubmit(): void {
    this.globalMessageResponse = '';

    this.articleService.postArticle(this.articleData)
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (response: GlobalMessageResponse) => {
          this.globalMessageResponse = 'Article créé avec succès !';
          this.articleForm.resetForm();

          this.articleData = {
            title: '',
            content: '',
            themeId: ''
          }
        },
        error: (error: Error) => {
          console.error(error);
          this.globalMessageResponse = 'Une erreur est survenue. Veuillez réessayer ultérieurement.';
        }
      })
  }
}
