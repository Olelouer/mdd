import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-homepage',
  imports: [MatButtonModule, RouterModule],
  templateUrl: './homepage.component.html',
})
export class HomepageComponent {

}
