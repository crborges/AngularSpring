import { Usuario } from './usuario.model';

export class Ticket{

   constructor(
      public id: string,
      public numero : number,
      public descricao : string,
      public titulo : string,
      public estado : string,
      public prioridade : string,
      public imagem: string,
      public usuario: Usuario,
      public usuarioAtribuido: Usuario,
      public data : string,
      public alteracoes :Array<string>
    ){}

}
