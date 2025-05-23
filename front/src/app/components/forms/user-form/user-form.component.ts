import { Component, DestroyRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user.service';
import { AuthService } from '../../../services/auth.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { UserInfos } from '../../../interfaces/user/user.interface';
import { FormsModule, NgForm } from '@angular/forms';
import { UserRequest } from '../../../interfaces/user/userRequest';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { GlobalMessageResponse } from '../../../interfaces/globalMessageResponse.interface';
import { Router } from '@angular/router';


@Component({
  selector: 'cpn-user-form',
  imports: [
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatIconModule,
    CommonModule,
    FormsModule
  ],
  templateUrl: './user-form.component.html'
})
export class UserFormComponent {
  @ViewChild('userForm') userForm!: NgForm;

  initialUserData: UserRequest = {
    username: '',
    email: '',
    password: ''
  };

  userData: UserRequest = {
    username: '',
    email: '',
    password: ''
  };

  public globalMessageResponse: string = '';

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private destroyRef: DestroyRef,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.loadCurrentUserInfos();
  }

  loadCurrentUserInfos(): void {

    this.userService.getCurrentUserInfos()
      .pipe(
        takeUntilDestroyed(this.destroyRef)
      )
      .subscribe({
        next: (response: UserInfos) => {
          this.initialUserData = {
            username: response.username,
            email: response.email,
            password: ''
          };

          this.userData.username = response.username;
          this.userData.email = response.email;
        },
        error: (error: Error) => {
          console.error(error);
        }
      })
  }

  onSubmit(): void {
    if (this.userForm.form.valid) {
      const updatedData: Partial<UserRequest> = {};

      if (this.userData.username !== this.initialUserData.username) {
        updatedData.username = this.userData.username;
      }
      if (this.userData.email !== this.initialUserData.email) {
        updatedData.email = this.userData.email;
      }
      if (this.userData.password) {
        updatedData.password = this.userData.password;
      }

      if (Object.keys(updatedData).length > 0) {
        this.userService.updateCurrentUser(updatedData)
          .pipe(
            takeUntilDestroyed(this.destroyRef)
          )
          .subscribe({
            next: (response: GlobalMessageResponse) => {
              this.globalMessageResponse = response.message;
              this.loadCurrentUserInfos();
              this.authService.logout();
              this.router.navigate(['/connection']);
            },
            error: (error: any) => {
              console.error(error);
              this.globalMessageResponse = 'Données entrées non compatibles. Veuillez réessayer.';
            }
          });
      } else {
        this.globalMessageResponse = 'Aucune modification détectée.';
      }
    }
  }
}
