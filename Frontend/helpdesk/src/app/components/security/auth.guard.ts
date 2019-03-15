import { Observable } from 'rxjs';
import { routes } from './../home/app.routes';
import { SharedService } from './../../services/shared.service';
import { Injectable } from "@angular/core";
import { Router, ActivatedRouteSnapshot ,RouterStateSnapshot, CanActivate} from '@angular/router';


@Injectable()
export class authGuard implements CanActivate{
  public shared :  SharedService;

  constructor(private router : Router){
    this.shared = SharedService.getInstance();
  }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) : Observable<boolean> | boolean{
      if (this.shared.isLoggedIn()){
          return true;
      }
      else {
          this.router.navigate(['/login']);
          return false;
      }

  }


}

