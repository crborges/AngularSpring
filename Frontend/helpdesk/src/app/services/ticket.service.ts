import { HELP_DESK_API } from './helpdesk.api';
import { Ticket } from './model/ticket.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class TicketService {



  constructor(private http: HttpClient) {}



  criarAtualizar(ticket: Ticket){
    if(ticket.id != null && ticket.id!=''){
        return this.http.put(`${HELP_DESK_API}/api/ticket`, ticket);
    }
    else{
      ticket.id = null;
      ticket.estado = 'NOVO';
      return this.http.post(`${HELP_DESK_API}/api/ticket`, ticket);
    }
  }



  buscarTodos(pagina: Number,contador: Number){
    return this.http.get(`${HELP_DESK_API}/api/ticket/${pagina}/${contador}`);
  }



  buscarPorId(id: string){
    return this.http.get(`${HELP_DESK_API}/api/ticket/${id}`);
  }



  deletar(id: string){
    return this.http.delete(`${HELP_DESK_API}/api/ticket/${id}`);
   }



  buscarPorParametros(pagina: Number,contador: Number, atribuidosAMim : boolean,ticket:Ticket) {
    ticket.numero       = ticket.numero       ==  '' ? 0                 : ticket.numero;
    ticket.titulo       = ticket.titulo       ==  '' ? 'nao_informado'   : ticket.titulo;
    ticket.estado       = ticket.estado       == null || '' ? 'nao_informado'   : ticket.estado;
    ticket.prioridade   = ticket.prioridade   == null || '' ? 'nao_informado'   : ticket.prioridade;

    console.log('|'+ticket.numero== ''+'|');

    console.log(`${HELP_DESK_API}/api/ticket/${pagina}/${contador}/${ticket.numero}/${ticket.titulo}/${ticket.estado}/${ticket.prioridade}/${atribuidosAMim}`);

    return this.http.get(`${HELP_DESK_API}/api/ticket/${pagina}/${contador}/${ticket.numero}/${ticket.titulo}/${ticket.estado}/${ticket.prioridade}/${atribuidosAMim}`);
  }


  atualizarSituacao(estado: string, ticket: Ticket) {
    return this.http.put(`${HELP_DESK_API}/api/ticket/${ticket.id}/${estado}`,ticket);
  }



  resumo() {
    return this.http.get(`${HELP_DESK_API}/api/ticket/resumo/`);
  }

}
