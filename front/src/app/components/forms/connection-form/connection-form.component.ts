import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../../services/auth.service';
import { AuthenticationRequest } from '../../../interfaces/auth/authenticationRequest.interface';
import { AuthenticationResponse } from '../../../interfaces/auth/authenticationResponse.interface';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'cpn-connection-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule, // Nécessaire pour NgForm, ngModel, etc.
    MatButtonModule
  ],
  templateUrl: './connection-form.component.html',
})
export class ConnectionFormComponent {
  @ViewChild('connectionForm') connectionForm!: NgForm;
  formData = {
    loginOrEmail: '',
    password: ''
  };

  errorMessage: string | null = null;

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  onSubmit(): void {
    this.errorMessage = null;

    const requestToSend: AuthenticationRequest = {
      password: this.formData.password
    };

    if (this.formData.loginOrEmail.includes('@')) {
      requestToSend.email = this.formData.loginOrEmail;
    } else {
      requestToSend.username = this.formData.loginOrEmail;
    }

    this.authService.login(requestToSend).subscribe({
      next: (response: AuthenticationResponse) => {
        this.router.navigate(['/feed']);
      },
      error: (error) => {
        console.error('Échec de la connexion:', error);
        this.formData.password = '';
      }
    });
  }
}