import { SharedService } from './services/shared.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  ShowTemplate:boolean=false;
  public shared: SharedService;
  constructor(){
    this.shared =  SharedService.getInstance();
  }


ngOnInit(){
  this.shared.ShowTemplate.subscribe(
    show=> this.ShowTemplate=show
  );
}

exibeBarraLateral():{}{
  return{
    'content-wrapper': this.shared.isLoggedIn()
  };
}

  title = 'helpdesk';
}
