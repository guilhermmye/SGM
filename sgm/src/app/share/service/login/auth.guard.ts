import {Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/share/service/login/token-storage.service';



@Injectable()
export class AuthGuard implements CanActivate{
  hasAccess: boolean = false;
  permissao: String[] = [];
  allowedRoles: String[] = [];
  constructor(private tokenStorageService:TokenStorageService,private router:Router) { }

  canActivate(
    route:ActivatedRouteSnapshot,
    state:RouterStateSnapshot
  ) : Observable<boolean> | boolean {
    
    this.allowedRoles = route.data.allowedRoles;
    
    if(this.tokenStorageService.getToken()){
      if(this.allowedRoles == undefined){
        return true;
      }else{
        if(this.checkAccess(this.allowedRoles)){
          return true
        }else{
          this.router.navigate(['/home']);
          return false;
        } 
      }       
    }else{
      this.router.navigate(['/login']);
      return false;
    }
    
  }

  private checkAccess(allowedRoles: String[]): boolean {
    this.hasAccess = false;
    this.permissao = this.tokenStorageService.getUser().roles;
    this.permissao.forEach(rol => {
      if (allowedRoles.findIndex(rols => rols === rol) >= 0) {
          this.hasAccess = true;
      }
  });     
     return this.hasAccess;  
}

}
