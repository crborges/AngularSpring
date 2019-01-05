import { Component } from '@angular/core';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'lista de tarefas';
  upperText :string ='Display uppercase text';
  lowerText :string ='Display lowercase text';
  percentual:  number = 0.5;
  sysdate:Date = new Date();
  grana:number = 600;
  isAdmin:boolean= false;
  profile:  number = 500;
  user : User={ nome:'Cristiano Borges', idade : 38  }


  /*-- criar um array para isnseri as tarefas simplesmente um atribut de classe
  //exportado para p compomente especializado nisto
  /*tasks=[];
  task="";
  add():void{
  //pega o array e para cada insersão no campo la no form eu insiro na ariavel aqui do sistmea e vou populando

    this.tasks.push(this.task);
  }
  */
}
