import { BrowserModule } from '@angular/platform-browser';
import { NgModule} from '@angular/core';
/* preciso dar o import de forms module para podser usar a diretiva ng-model e pegar valor do formulario e jogar para a classe */
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TaskListComponent } from './task-list/task-list.component';

@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent
  ],
  /*
  importo o  forms module para poder pegar coisad o fore
  */
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
