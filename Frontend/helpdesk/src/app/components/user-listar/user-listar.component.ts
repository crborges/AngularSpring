import { SharedService } from './../../services/shared.service';
import { routes, ROUTES } from './../../app.routes';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import { DialogService } from 'src/app/dialog.service';
import { RetornoAPI } from 'src/app/services/model/retorno.api';

@Component({
  selector: 'app-user-listar',
  templateUrl: './user-listar.component.html',
  styleUrls: ['./user-listar.component.css']
})
export class UserListarComponent implements OnInit {

  page: number=0;
  contador:number=5;
  pages: Array<number>;
  shared: SharedService;
  mensagem: {};
  classCSS: {};
  listaUsuarios: [];


  constructor(private dialogService: DialogService,private userService: UserService, private rota: ROUTES) {
    this.shared = SharedService.getInstance();
   }

  ngOnInit() {
    this.findAll(this.page, this.contador);
  }

  findAll(page: number, contador: number){
      this.userService.buscarTodos(page,contador).subscribe((retornoApi: RetornoAPI)=>{
          this.listaUsuarios=retornoApi['data']['content'];
          this.pages=new Array(retornoApi['data']['totalPages']);

      },err=>{
        this.showMessage({
          type:'error',
          text : err['error']['errors'][0]
        });
      });
  }



  edit(id:string){
  this.rota.navigate(['/usuario-novo',id]);
  }
  delete(id:string){
    this.dialogService.confirmar('tem certeza que deseja excluir este usuário?').then((deletavel:boolean)=>{
        if(deletavel){
          this.mensagem={};
          this.userService.deletar(id).subscribe((respostaApi:RetornoAPI)=>{
              this.showMessage({
                type:'sucees',
                text: 'Usuário deletado'
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
