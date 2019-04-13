import { Resumo } from './../../services/model/resumo.model';
import { Component, OnInit } from '@angular/core';
import { TicketService } from 'src/app/services/ticket.service';
import { RetornoAPI } from 'src/app/services/model/retorno.api';

@Component({
  selector: 'app-resumo',
  templateUrl: './resumo.component.html',
  styleUrls: ['./resumo.component.css']
})
export class ResumoComponent implements OnInit {


  resumo: Resumo = new Resumo();
  mensagem:{};
  classCSS:{};



  constructor(private ticketService : TicketService) { }

  ngOnInit() {
    this.ticketService.resumo().subscribe((respostaAPI: RetornoAPI)=>{
      this.resumo=respostaAPI.dado;
          },err=>{
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
          } );

  }



























  private showMessage(message: {type : string, text: string}): void{
    this.mensagem= message;
    this.CriaClasseCSS(message.type);
    setTimeout(() => {
        this.mensagem=undefined;
    }, 3000);

  }


  private CriaClasseCSS(type: string):void{
    this.classCSS={
      'alert': true
    }
    this.classCSS['alert-'+type] = true;

  }

  getFormValido(isInvalido: boolean,isDirty):{}{
    return {
      'form-group': true,
      'has-error': isInvalido && isDirty,
      'has-success': !isInvalido && !isDirty
    };
  }
}
