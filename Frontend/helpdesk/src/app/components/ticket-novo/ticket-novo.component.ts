import { Ticket } from 'src/app/services/model/ticket.model';
import { TicketService } from '../../services/ticket.service';
import { SharedService } from 'src/app/services/shared.service';
import { RetornoAPI } from 'src/app/services/model/retorno.api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-ticket-novo',
  templateUrl: './ticket-novo.component.html',
  styleUrls: ['./ticket-novo.component.css']
})
export class TicketNovoComponent implements OnInit {

  @ViewChild("form_ticket_novo")
  form_ticket_novo : NgForm;

  ticket=  new Ticket('',0,'','','', '','',null,null,'',null);
  shared : SharedService;
  mensagem:{};
  classCSS:{};

;


constructor(private ticketService : TicketService, private rotas: ActivatedRoute) {
    this.shared= SharedService.getInstance();
   }


  ngOnInit() {
    let id : string = this.rotas.snapshot.params['id'];
      if(id != undefined){
          this.findByID(id);
      }
  }



  findByID(id: string){
    this.ticketService.buscarPorId(id).subscribe((respostaAPI: RetornoAPI)=>{
      this.ticket=respostaAPI.dado;
          },err=>{
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
          } );
  }



  salvarTicket(){
    this.mensagem={};
    this.ticketService.criarAtualizar(this.ticket).subscribe((respostaAPI: RetornoAPI)=>{
      this.ticket =  new Ticket('',0,'','','', '','',null,null,'',null);
      let ticketRetorno: Ticket= respostaAPI.dado;
      this.form_ticket_novo.resetForm();//executa ok
      this.showMessage({ //
        type: 'sucess',
        text: 'Ticket criado com sucesso'
      });
    },err=>{
      this.showMessage({
        type: 'error',
        text: err['error']['message']
      });

    });
  }


  onfileChange(event): void{
    if(event.target.files[0]>2000000){
      this.showMessage({
        type: 'error',
        text:' tamanho maximo da iamggem e de 2 mb'
      });
    }
    else{
      this.ticket.imagem='';
      var reader = new FileReader();
      reader.onloadend=(e: Event)=>{
      this.ticket.imagem =reader.result;
      }
      reader.readAsDataURL(event.target.files[0])
    }
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
