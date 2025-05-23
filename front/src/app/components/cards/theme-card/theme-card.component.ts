import { Component, DestroyRef, effect, EventEmitter, input, Output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Theme } from '../../../interfaces/theme/theme.interface';
import { ThemeService } from '../../../services/theme.service';

import { MatButtonModule } from '@angular/material/button';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { GlobalMessageResponse } from '../../../interfaces/globalMessageResponse.interface';

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
  forUnsubscribe = input<boolean>(false);

  isSubscribed = signal(false);

  @Output() subscriptionChanged = new EventEmitter<{ themeId: number, subscribed: boolean }>();

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
        next: (response: GlobalMessageResponse) => {
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
        next: (response: GlobalMessageResponse) => {
          this.isSubscribed.set(false);
          this.subscriptionChanged.emit({ themeId: currentTheme.id, subscribed: false });
        }
      });
  }

}
