import { Injectable } from "@angular/core";

@Injectable()
export class DialogService{

  confirmar(mensagem?:string){
    return new Promise(resolve=>{
      return resolve(window.confirm(mensagem || 'Confirmar ?'));
    });
  }
}
