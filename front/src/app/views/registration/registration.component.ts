import { Component } from '@angular/core';

import { HeaderComponent } from '../../components/header/header.component';
import { RegistrationFormComponent } from '../../components/forms/registration-form/registration-form.component';
import { BackArrowComponent } from '../../components/back-arrow/back-arrow.component';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-registration',
  imports: [HeaderComponent, RegistrationFormComponent, BackArrowComponent],
  templateUrl: './registration.component.html',
})
export class RegistrationComponent {
  constructor(private titleService: Title) {
  }

  ngOnInit(): void {
    this.titleService.setTitle("Inscription - MDD")
  }
}
