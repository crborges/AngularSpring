import { SharedService } from './../../services/shared.service';
import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/services/model/usuario.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  shared : SharedService;

  constructor() {
    this.shared= SharedService.getInstance();
    this.shared.usuario= new Usuario('','','','');
   }

  ngOnInit() {  }

  logout():void{
      this.shared.token=null;
      this.shared.usuario=null;
      window.location.href='/login';
      window.location.reload();

  }

}

