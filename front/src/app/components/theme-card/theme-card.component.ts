import { Component, Input } from '@angular/core';
import { Theme } from '../../interfaces/theme/theme.interface';

import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'cpn-theme-card',
  imports: [
    MatButtonModule
  ],
  templateUrl: './theme-card.component.html',
})
export class ThemeCardComponent {
  @Input() theme?: Theme;

  subscribe() {

  }
}
