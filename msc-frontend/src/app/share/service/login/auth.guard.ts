import {Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/share/service/login/token-storage.service';



@Injectable()
export class AuthGuard implements CanActivate{

  constructor(private tokenStorageService:TokenStorageService,private router:Router) { }

  canActivate(
    route:ActivatedRouteSnapshot,
    state:RouterStateSnapshot
  ) : Observable<boolean> | boolean {

    if(this.tokenStorageService.getToken()){
      return true;
    }else{
      this.router.navigate(['/login']);
      return false;
    }
    
  }

}
