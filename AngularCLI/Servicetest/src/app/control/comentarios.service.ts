import { Comentario } from './../model/comentario';

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ComentariosService {

  /* Inicia o servico http pois precisa se comunicar com um serviço rest externo */
  constructor(private http: HttpClient) { }

  /* Metodo que chama um servico rest que responde via verbo GET os comentarios   */
  /* reto0rna um observable que fica monitorando as alterações para avisar aos envolvidos */
  getCommentarios(): Observable<Comentario[]>{

        return this.http.get<Comentario[]>('https://jsonplaceholder.typicode.com/comments');
  }

}


