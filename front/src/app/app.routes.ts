import { Routes } from '@angular/router';
import { HomepageComponent } from './views/homepage/homepage.component';
import { RegistrationComponent } from './views/registration/registration.component';
import { ConnectionComponent } from './views/connection/connection.component';

export const routes: Routes = [
    { path: '', component: HomepageComponent },

    { path: 'register', component: RegistrationComponent },
    { path: 'connection', component: ConnectionComponent },
];
