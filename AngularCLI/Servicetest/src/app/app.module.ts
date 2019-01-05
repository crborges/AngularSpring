import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';

/* Dentro do meu escopo */
import { AppComponent } from './app.component';
import { ComentariosComponent } from './comentarios/comentarios.component';
import { ComentariosService } from './control/comentarios.service';




@NgModule({

  /* Declaracao dos componentes da minha APP */
  declarations: [AppComponent,ComentariosComponent],

  /*Imports a serem utilizados */
  imports: [BrowserModule,AppRoutingModule,HttpClientModule],

  /* Provedores de acesso a dados e servicos de todo o app */
  providers: [ComentariosService],

  /* Array de bootstrap de componentes esse array tem quais as arvoires de componentes que a aplicacao vai usar e iniciar (filhos) e recomentradoq ue se tenha uma arvore unica */
  bootstrap: [AppComponent]

})
export class AppModule { }
