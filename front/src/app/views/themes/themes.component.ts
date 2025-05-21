import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Title } from '@angular/platform-browser';
import { ThemeService } from '../../services/theme.service';
import { finalize, Observable } from 'rxjs';
import { Theme } from '../../interfaces/theme/theme.interface';

import { HeaderComponent } from '../../components/header/header.component';
import { ThemeCardComponent } from '../../components/cards/theme-card/theme-card.component';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


@Component({
  selector: 'app-themes',
  imports: [
    CommonModule,
    HeaderComponent,
    ThemeCardComponent,
    MatProgressSpinnerModule
  ],
  templateUrl: './themes.component.html'
})
export class ThemesComponent {
  public isLoading: boolean = false;
  public themes$!: Observable<Theme[]>

  constructor(private themeService: ThemeService, private titleService: Title) { }

  ngOnInit(): void {
    this.titleService.setTitle("Liste des thèmes - MDD")
    this.loadThemes();
  }

  loadThemes(): void {
    this.isLoading = true;
    this.themes$ = this.themeService.getAllWithSubscriptions().pipe(
      finalize(() => {
        this.isLoading = false;
      })
    )
  }

}
