import { HELP_DESK_API } from './helpdesk.api';
import { Usuario } from './model/usuario.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}



  login(usuario : Usuario){
      return this.http.post(`${HELP_DESK_API}/api/auth`,usuario);
  }



  criarAtualizar(usuario: Usuario){
    if(usuario.id!=null && usuario.id!=''){
        return this.http.put(`${HELP_DESK_API}/api/usuario`, usuario);
    }
    else{
       usuario.id= null; return this.http.post(`${HELP_DESK_API}/api/usuario`, usuario);
    }
  }



  buscarTodos(pagina: Number,contador: Number){
    return this.http.get(`${HELP_DESK_API}/api/usuario/${pagina}/${contador}`);
  }



  buscarPorId(id: string){
    return this.http.get(`${HELP_DESK_API}/api/usuario/${id}`);
  }


 deletar(id: string){
  return this.http.delete(`${HELP_DESK_API}/api/usuario/${id}`);
 }

}
