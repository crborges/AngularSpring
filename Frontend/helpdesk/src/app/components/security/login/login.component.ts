import { CurrentUser } from '../../../services/model/current.user.model';
import { routes } from '../../../app.routes';
import { UserService } from './../../../services/user.service';
import { SharedService } from './../../../services/shared.service';
import { Usuario } from './../../../services/model/usuario.model';
import { Component, OnInit } from '@angular/core';
import { RouteReuseStrategy, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario = new Usuario('','','','');
  shared : SharedService;
  message: string;

  constructor(private userService: UserService, private router : Router) {
    this.shared= SharedService.getInstance();
  }

  ngOnInit() { }

  login(){


    this.message="";
    console.log(this);
    this.userService.login(this.usuario).subscribe((usuarioAutenticado: CurrentUser)=>{
        this.shared.token= usuarioAutenticado.token;
        this.shared.usuario=usuarioAutenticado.usuario;
        if      (usuarioAutenticado.usuario.perfil=="ROLE_ADMIN")     {this.shared.usuario.perfil="Administrador" ;}
        else if (usuarioAutenticado.usuario.perfil=="ROLE_CLIENTE")   {this.shared.usuario.perfil="Cliente"       ;}
        else if (usuarioAutenticado.usuario.perfil=="ROLE_TECNICO ")  {this.shared.usuario.perfil="Técnico"       ;}
        else                                                          {this.shared.usuario.perfil="Desconhecido"  ;}
        this.shared.ShowTemplate.emit(true);
        this.router.navigate(['/']);
    },err=>{
      this.shared.token=null;
      this.shared.usuario=null;
      this.shared.ShowTemplate.emit(false);
      this.message='Erro inesperado ao logar';
    });
  }

  cancelarLogin(){
    this.message='';
    this.usuario =new Usuario('','','','');
    window.location.href='/login';
    window.location.reload();

  }


  getFormValido(isInvalido: boolean,isDirty):{}{
    return {
      'form-group': true,
      'has-error': isInvalido && isDirty,
      'has-success': !isInvalido && !isDirty
    };
  }
}
