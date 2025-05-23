import { Component, DestroyRef } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { HeaderComponent } from '../../components/header/header.component';
import { ThemeCardComponent } from '../../components/cards/theme-card/theme-card.component';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { ThemeService } from '../../services/theme.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Theme } from '../../interfaces/theme/theme.interface';


@Component({
  selector: 'app-user-profile',
  imports: [
    HeaderComponent,
    ThemeCardComponent,
    MatProgressSpinnerModule
  ],
  templateUrl: './user-profile.component.html',
})
export class UserProfileComponent {
  public themes!: Theme[];
  public isLoading = false;

  constructor(
    private themeService: ThemeService,
    private destroyRef: DestroyRef,
    private titleService: Title
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle("Profil - MDD");
    this.loadSubscribedThemes();
  }

  loadSubscribedThemes(): void {
    this.isLoading = true;
    this.themeService.getSubscribedThemes()
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (response: Theme[]) => {
          this.themes = response;
          this.isLoading = false;
        },
        error: (error: Error) => {
          console.error(error);
          this.isLoading = false;

        }
      })
  }

  onThemeSubscriptionChange(event: { themeId: number, subscribed: boolean }): void {
    if (!event.subscribed) {
      this.themes = this.themes.filter(theme => theme.id !== event.themeId);
    }
  }
}
