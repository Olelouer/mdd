import { Component } from '@angular/core';

import { HeaderComponent } from '../../components/header/header.component';
import { RegistrationFormComponent } from '../../components/forms/registration-form/registration-form.component';
import { BackArrowComponent } from '../../components/back-arrow/back-arrow.component';


@Component({
  selector: 'app-registration',
  imports: [HeaderComponent, RegistrationFormComponent, BackArrowComponent],
  templateUrl: './registration.component.html',
})
export class RegistrationComponent {

}
