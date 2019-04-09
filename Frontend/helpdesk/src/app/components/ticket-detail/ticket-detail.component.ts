import { Ticket } from 'src/app/services/model/ticket.model';
import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import { TicketService } from 'src/app/services/ticket.service';
import { ActivatedRoute } from '@angular/router';
import { RetornoAPI } from 'src/app/services/model/retorno.api';

@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {


  ticket=  new Ticket('',0,'','','', '','',null,null,'',null);
  shared : SharedService;
  mensagem:{};
  classCSS:{};

  constructor(private ticketService : TicketService, private rotas: ActivatedRoute) {
    this.shared= SharedService.getInstance();
   }


  ngOnInit() {
    let id:string=this.rotas.snapshot.params['id'];
    if(id!=undefined){
        this.findByID(id);
    }
  }


  findByID(id: string){
    this.ticketService.buscarPorId(id).subscribe((respostaAPI: RetornoAPI)=>{
      this.ticket=respostaAPI.dado;
      this.ticket.data= new Date(this.ticket.data).toISOString();

          },err=>{
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
          } );
  }


  AlterarTicket(estado:string): void {
    this.ticketService.atualizarSituacao(estado,this.ticket).subscribe((respostaAPI: RetornoAPI)=>{
        this.ticket =   respostaAPI.dado;
        this.ticket.data= new Date(this.ticket.data).toISOString();
        this.showMessage({ //
          type: 'success',
          text: 'Ticket atualizado com sucesso'
        });
      },err=>{
        this.showMessage({
          type: 'error',
          text: err['error']['errors'][0]
        });

      });
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
