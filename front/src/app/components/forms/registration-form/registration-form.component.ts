import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { RegisterRequest, AuthenticationResponse } from '../../../interfaces/auth.interface';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'cpn-registration-form',
  imports: [FormsModule, CommonModule, MatButtonModule],
  templateUrl: './registration-form.component.html',
})
export class RegistrationFormComponent {
  registrationData: RegisterRequest = {
    username: '',
    email: '',
    password: ''
  }

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    this.authService.register(this.registrationData).subscribe({
      next: (response: AuthenticationResponse) => {
        console.log('Success: ' + response);
      },
      error: (error: Error) => { console.log(error) }
    });

  }
}
