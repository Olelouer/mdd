import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'cpn-back-arrow',
  imports: [RouterLink],
  templateUrl: './back-arrow.component.html'
})
export class BackArrowComponent {
  @Input() backRoute: string = '/';
}
