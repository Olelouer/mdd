import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HeaderComponent } from '../../components/header/header.component';
import { ConnectionFormComponent } from '../../components/forms/connection-form/connection-form.component';
import { BackArrowComponent } from '../../components/back-arrow/back-arrow.component';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-connection',
  imports: [RouterModule, HeaderComponent, ConnectionFormComponent, BackArrowComponent],
  templateUrl: './connection.component.html',
})
export class ConnectionComponent {
  constructor(private titleService: Title) {
  }

  ngOnInit(): void {
    this.titleService.setTitle("Connexion - MDD")
  }
}
