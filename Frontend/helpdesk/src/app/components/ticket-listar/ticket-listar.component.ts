import { TicketService } from './../../services/ticket.service';
import { Ticket } from './../../services/model/ticket.model';
import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import {  Router } from '@angular/router';
import { DialogService } from 'src/app/dialog.service';
import { RetornoAPI } from 'src/app/services/model/retorno.api';


@Component({
  selector: 'app-ticket-listar',
  templateUrl: './ticket-listar.component.html',
  styleUrls: ['./ticket-listar.component.css']
})
export class TicketListarComponent implements OnInit {


  designadosAMin: boolean=false;
  page: number=0;
  contador:number=10;
  pages: Array<number>;
  shared: SharedService;
  mensagem: {};
  classCSS: {};
  listaTickets: [];
  ticketFiltro =  new Ticket('',0,'','','', '','',null,null,'',null);



  constructor(private dialogService: DialogService,private ticketService: TicketService, private rota : Router) {
      this.shared = SharedService.getInstance();
   }

  ngOnInit() { this.findAll(this.page, this.contador);  }


  findAll(page: number, contador: number){
    this.ticketService.buscarTodos(page,contador).subscribe((retornoApi: RetornoAPI)=>{
        this.listaTickets=retornoApi['dado']['content'];
        this.pages=new Array(retornoApi['dado']['totalPages']);
    },err=>{
      this.showMessage({
        type:'error',
        text : err['error']['message']
      });
    });
  }



  findByParameters(page: number, contador: number){
    this.page=0;
    this.contador=10;
    console.log(this.ticketFiltro);
    this.ticketService.buscarPorParametros(this.page,this.contador,this.designadosAMin,this.ticketFiltro)
      .subscribe((retornoApi: RetornoAPI)=>{
      this.ticketFiltro.titulo = this.ticketFiltro.titulo == 'nao_informado' ? '' : this.ticketFiltro.titulo;
      this.ticketFiltro.estado = this.ticketFiltro.estado == 'nao_informado' ? '' : this.ticketFiltro.estado;
      this.ticketFiltro.prioridade = this.ticketFiltro.prioridade == 'nao_informado' ? '' : this.ticketFiltro.prioridade;
      this.ticketFiltro.numero = this.ticketFiltro.numero ==  null ? 0 : this.ticketFiltro.numero;
      this.listaTickets=retornoApi['dado']['content'];
      this.pages=new Array(retornoApi['dado']['totalPages']);
    },err=>{
    this.showMessage({
      type:'error',
      text : err['error']['message']
    });
  });
  }


  limparFiltros():void {
    this.designadosAMin=false;
    this.page=0;
    this.contador=10;
    this.ticketFiltro=  new Ticket('',0,'','','', '','',null,null,'',null);
    this.findAll(this.page, this.contador);
  }



    editar(id:string){
    this.rota.navigate(['/ticket-novo',id]);
    }

    detalhes(id:string){
      this.rota.navigate(['/ticket-detalhe',id]);
    }

    deletar(id:string){
      this.dialogService.confirmar('tem certeza que deseja excluir este Ticket?').then((deletavel:boolean)=>{
          if(deletavel){
            this.mensagem={};
            this.ticketService.deletar(id).subscribe((respostaApi:RetornoAPI)=>{
                this.showMessage({
                  type:'success',
                  text: 'Ticket deletado'
                });
                this.findAll(this.page,this.contador);
            },err=>{
              this.showMessage({
                type:'error',
                text: err['error']['errors'][0]
              });
            });
          }
      });
    }













  setProximaPagina(event:any){
    event.preventDefault();
    if(this.page+1<this.pages.length){
      this.page= this.page+1;
      this.findAll(this.page,this.contador);
    }
  }


  setPaginaAnterior(event:any){
    event.preventDefault();
    if(this.page>0){
      this.page= this.page-1;
      this.findAll(this.page,this.contador);
    }
  }


  setPagina(i,event:any){
    event.preventDefault();
      this.page= i;
      this.findAll(this.page,this.contador);
  }

  private showMessage(message: {type : string, text: string}): void{
    this.mensagem= message;
    this.CriaClasseCSS(message.type);
    setTimeout(() => {
        this.mensagem=undefined;
    }, 3000);

  }


  private CriaClasseCSS(type: string):void{
    console.log('tipo do alerta alert-'+type);
    this.classCSS={
      'alert': true
    }
    this.classCSS['alert-'+type] = true;

  }

}
