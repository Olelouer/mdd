import { Component, DestroyRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { RegisterRequest } from '../../../interfaces/auth/registerRequest.interface';
import { AuthenticationResponse } from '../../../interfaces/auth/authenticationResponse.interface';
import { MatButtonModule } from '@angular/material/button';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

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

  constructor(
    private authService: AuthService,
    private router: Router,
    private destroyRef: DestroyRef
  ) { }

  onSubmit(): void {
    this.authService.register(this.registrationData)
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (response: AuthenticationResponse) => {
          this.router.navigate(['/feed']);
        },
        error: (error: Error) => { console.error(error) }
      });

  }
}
