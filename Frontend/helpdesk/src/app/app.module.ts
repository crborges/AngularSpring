import { UserListarComponent } from './components/user-listar/user-listar.component';
import { DialogService } from 'src/app/dialog.service';
import { authGuard } from './components/security/auth.guard';
import { AuthInterceptor } from './components/security/auth.interceptor';
import { SharedService } from './services/shared.service';
import { UserService } from './services/user.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/security/login/login.component';
import { routes } from './app.routes';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule} from '@angular/forms';
import { UserNovoComponent } from './components/user-novo/user-novo.component';


@NgModule({
  declarations: [
      AppComponent,
      HeaderComponent,
      MenuComponent,
      FooterComponent,
      HomeComponent,
      LoginComponent,
      UserNovoComponent,
      UserListarComponent
  ],
  imports: [
      BrowserModule,
      HttpClientModule,
      FormsModule,
      routes,
  ],
  providers: [
      UserService,
      SharedService,
      DialogService,
      authGuard,
      {
        provide : HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
      }
  ],
  bootstrap: [
      AppComponent,
      HeaderComponent,
      MenuComponent,
      FooterComponent
  ]
})
export class AppModule { }


