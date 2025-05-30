import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-homepage',
  imports: [MatButtonModule, RouterModule],
  templateUrl: './homepage.component.html',
})
export class HomepageComponent {
  constructor(private titleService: Title) {
  }

  ngOnInit(): void {
    this.titleService.setTitle("MDD")
  }
}
