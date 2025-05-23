import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-not-found',
  imports: [MatButtonModule, RouterModule],
  templateUrl: './not-found.component.html'
})
export class NotFoundComponent {
  constructor(private titleService: Title) {
  }

  ngOnInit(): void {
    this.titleService.setTitle("404 - MDD")
  }

}
