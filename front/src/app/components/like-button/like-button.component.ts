import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'cpn-like-button',
  imports: [],
  templateUrl: './like-button.component.html'
})
export class LikeButtonComponent {
  @Input() likeCount?: number;
  @Input() itemId?: number;
  @Input() liked?: boolean;

  @Output() toggleLike = new EventEmitter<void>;

  likeToggle() {
    this.toggleLike.emit();
  }
}
