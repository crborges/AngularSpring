import { TicketListarComponent } from './components/ticket-listar/ticket-listar.component';
import { TicketNovoComponent } from './components/ticket-novo/ticket-novo.component';
import { UserListarComponent } from './components/user-listar/user-listar.component';
import { UserNovoComponent } from './components/user-novo/user-novo.component';
import { authGuard } from './components/security/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/security/login/login.component';
import { Routes, RouterModule,  } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';


export const ROUTES: Routes =[
  {path:'',component: HomeComponent,canActivate: [authGuard]},
  {path:'login',component: LoginComponent},
  {path:'usuario-novo', component: UserNovoComponent,canActivate: [authGuard]},
  {path:'usuario-novo/:id', component: UserNovoComponent,canActivate: [authGuard]},
  {path:'usuario-lista', component: UserListarComponent,canActivate: [authGuard]},
  {path:'ticket-novo', component: TicketNovoComponent,canActivate: [authGuard]},
  {path:'ticket-novo/:id', component: TicketNovoComponent,canActivate: [authGuard]},
  {path:'ticket-lista', component: TicketListarComponent,canActivate: [authGuard]}
  /*
  {path:'ticket-detalhe', component: TicketListarComponent,canActivate: [authGuard]}


  {path:'resumo', component: ResumoComponent,canActivate: [authGuard]}
  */



]


export const routes: ModuleWithProviders= RouterModule.forRoot(ROUTES);
//export class rotas{}
