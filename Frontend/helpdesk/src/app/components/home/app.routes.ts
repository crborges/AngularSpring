import { authGuard } from './../security/auth.guard';
import { HomeComponent } from './home.component';
import { LoginComponent } from './../security/login/login.component';
import { Routes,RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

export const ROUTES: Routes =[
  {path:'',component: HomeComponent,canActivate: [authGuard]},
  {path:'login',component: LoginComponent},
]


export const routes: ModuleWithProviders= RouterModule.forRoot(ROUTES);
//export class rotas{}
