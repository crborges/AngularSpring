import { UserService } from './../../services/user.service';
import { Usuario } from './../../services/model/usuario.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SharedService } from 'src/app/services/shared.service';
import { ActivatedRoute } from '@angular/router';
import { RetornoAPI} from './../../services/model/retorno.api';

@Component({
  selector: 'app-user-novo',
  templateUrl: './user-novo.component.html',
  styleUrls: ['./user-novo.component.css']
})
export class UserNovoComponent implements OnInit {


  @ViewChild("form_user_novo")
  form_user_novo: NgForm;

  usuario = new Usuario('','','','');
  shared : SharedService;
  mensagem:{};
  classCSS:{};

  constructor(private usuarioService : UserService, private rotas: ActivatedRoute) {
    this.shared= SharedService.getInstance();
  }

  ngOnInit() {
    let id : string = this.rotas.snapshot.params['id'];
      if(id != undefined){
          this.findByID(id);
      }

  }

  findByID(id: string){
    this.usuarioService.buscarPorId(id).subscribe((respostaAPI: RetornoAPI)=>{
      this.usuario=respostaAPI.dado;
      this.usuario.senha='';
          },err=>{
              this.showMessage({
                type: 'error',
                text: err['error']['errors'][0]
              });
          } );
  }

  salvarUsuario(){
    this.mensagem={};
    this.usuarioService.criarAtualizar(this.usuario).subscribe((respostaAPI: RetornoAPI)=>{
      this.usuario = new Usuario('','','','');
      let usuarioRetorno: Usuario= respostaAPI.dado;
      console.log('trabalho com ');//ok
      console.log(respostaAPI.dado.email);//ok assim
      this.form_user_novo.resetForm();//executa ok
      console.log('pos execucao ');//executa ok
      this.showMessage({ //
        type: `success`,
        text: `usuário criado ${respostaAPI.dado.email} com sucesso`
      });
      console.log('depois de tudo');
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
    console.log('tipo do alerta alert-'+type);
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
