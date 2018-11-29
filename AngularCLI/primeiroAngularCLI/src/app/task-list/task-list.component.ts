import { Component, OnInit } from '@angular/core';

@Component({
    /*
    essa e a tag do meu componente depois que el estiver criado eu posso chamar ele como uma tag htm na minha app
    <app-task-list></app-task-list>
    */
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent {
  title = 'lista de tarefas';
  /*
    criar um array para isnseri as tarefas simplesmente um atribut de classe
    exportado para p compomente especializado nisto
  */
   tasks=[];
   task="";


   add():void{
  /*
  pega o array e para cada insersão no campo la no form eu insiro na ariavel aqui do sistmea e vou populando
  */
    this.tasks.push(this.task);
  }

}
