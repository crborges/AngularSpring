import { Comentario } from './../model/comentario';
import { ComentariosService } from './../control/comentarios.service';
import { Component, OnInit } from '@angular/core';


/*
descritor do componente para ser chamado dentro do dom
  selector:         nome do seletor que ele vai reponder dentro do dom
  templateUrl:      Nome do html do elemento
  styleUrls:        links dos css do componente
*/
@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})

/* implementa a interface on init para que ao bootar a classe  execute determinadas acoes */
export class ComentariosComponent implements OnInit {

   /* cria um array para receber a lista de comentarios */
  comentarios: Comentario [];
  /* Cria uma instancia  do controle para acessar os dados que vamos trabalhar na view */
  constructor(private ComentariosService: ComentariosService) { }

  /* implementa o metodo de incializacao da interface oninit  onde vou inicializar coisas para jogar na gui por exemplo*/
  ngOnInit() {
    /* Pega a instancia do controller e chama o metodo de get objs, neste metodo se inscreve no observable dele para tratar  o que vier do fluxo o tratmento vai ser cujspir o que vem do fluxo num array de comentarios e disponiblizar na gui esse array*/
      this.ComentariosService.getCommentarios()
      .subscribe(comentarios => this.comentarios = comentarios);
      /*cria uma variavel para disponibilizar na gui o nome dela vai ser  comentarios
        em que comentarios => comentarios recebe .... que é a lista de comentarios vazia que eu criei e que dentro dessa lista vazia
     */


  }

}
