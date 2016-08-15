import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from './home/home.component';
import {PostComponent} from './post/post.component';

const appRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'posts', component: PostComponent }
];
export const appRouterProviders: any[] = [
];
export const routing = RouterModule.forRoot(appRoutes) ;