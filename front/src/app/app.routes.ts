import { Routes } from '@angular/router';
import { HomepageComponent } from './views/homepage/homepage.component';
import { RegistrationComponent } from './views/registration/registration.component';
import { ConnectionComponent } from './views/connection/connection.component';
import { FeedComponent } from './views/feed/feed.component';
import { authGuard } from './guards/auth.guard';
import { guestGuard } from './guards/guest.guard';
import { ThemesComponent } from './views/themes/themes.component';
import { SingleArticleComponent } from './views/article/single-article.component';
import { CreateArticleComponent } from './views/create-article/create-article.component';
import { NotFoundComponent } from './views/not-found/not-found.component';
import { UserProfileComponent } from './views/user-profile/user-profile.component';


export const routes: Routes = [
    { path: '', component: HomepageComponent, canActivate: [guestGuard] },
    { path: 'register', component: RegistrationComponent, canActivate: [guestGuard] },
    { path: 'connection', component: ConnectionComponent, canActivate: [guestGuard] },

    { path: 'feed', component: FeedComponent, canActivate: [authGuard] },
    { path: 'themes', component: ThemesComponent, canActivate: [authGuard] },
    { path: 'create-article', component: CreateArticleComponent, canActivate: [authGuard] },
    { path: 'article/:id', component: SingleArticleComponent, canActivate: [authGuard] },
    { path: 'user-profile', component: UserProfileComponent, canActivate: [authGuard] },

    { path: '**', component: NotFoundComponent },
];
