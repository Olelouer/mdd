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
  isLoading: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    this.isLoading = true;

    this.authService.register(this.registrationData).subscribe({
      next: (response: AuthenticationResponse) => {
        this.isLoading = false;
        console.log('Success: ' + response);
      },
      error: (error: Error) => { console.log(error) }
    });

  }
}
