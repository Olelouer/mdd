import { Component, DestroyRef, effect, input, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Theme } from '../../../interfaces/theme/theme.interface';
import { ThemeService } from '../../../services/theme.service';

import { MatButtonModule } from '@angular/material/button';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

@Component({
  selector: 'cpn-theme-card',
  imports: [
    CommonModule,
    MatButtonModule
  ],
  templateUrl: './theme-card.component.html',
})
export class ThemeCardComponent {
  theme = input.required<Theme>();

  isSubscribed = signal(false);

  constructor(private themeService: ThemeService, private destroyRef: DestroyRef) {
    effect(() => {
      this.isSubscribed.set(this.theme().subscribed);
    });
  }

  subscribe(): void {
    const currentTheme = this.theme();
    this.themeService.subscribeCurrentUser(currentTheme.id)
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: () => {
          this.isSubscribed.set(true);
        }
      });
  }

  unsubscribe(): void {
    const currentTheme = this.theme();
    this.themeService.unsubscribeCurrentUser(currentTheme.id)
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: () => {
          this.isSubscribed.set(false);
        }
      });
  }

}
