import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'cpn-header',
  imports: [RouterModule, CommonModule],
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  private authService = inject(AuthService);
  private router = inject(Router);
  isPanelOpen: boolean = false;
  isUserProfileActive = false;

  ngOnInit(): void {
    if (this.router.url === '/user-profile') {
      this.isUserProfileActive = true;
    }
  }

  get userLogged(): boolean {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(["/"]);
  }

  redirectHome() {
    if (this.userLogged) {
      this.router.navigate(["/feed"]);
    } else {
      this.router.navigate(["/"]);
    }
  }

  openPanel(): void {
    this.isPanelOpen = true;
  }

  closePanel(): void {
    this.isPanelOpen = false;
  }

} 
