import { Component, inject } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'cpn-header',
  imports: [],
  templateUrl: './header.component.html',
})
export class HeaderComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  get userLogged(): boolean {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate([""]);
  }

  redirectHome() {
    if (this.userLogged) {
      this.router.navigate(["/feed"]);
    } else {
      this.router.navigate([""]);
    }
  }

} 
