import { EventEmitter, Injectable } from '@angular/core';

const TOKEN_KEY  = 'auth-token';
const USER_KEY  = 'auth-user';
const ROLE_ADM  = 'ROLE_ADMIN';
const ROLE_TEC  = 'ROLE_TECNICO';
const ROLE_USER  = 'ROLE_USUARIO';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  mostrarMenuEmitter = new EventEmitter<boolean>();
  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): any {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user:any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    return JSON.parse(window.sessionStorage.getItem(USER_KEY) || '{}');
  }

public permissaoAdm(): any{
  return this.possuiAutorizacao(ROLE_ADM);
}
public permissaoTecnico(): any{
  return this.possuiAutorizacao(ROLE_TEC);
}
public permissaoUsuario(): any{
  return this.possuiAutorizacao(ROLE_USER);;
}

public possuiAutorizacao(permissao:any): boolean {
    let resultado = false
    const roles: [] = this.getUser().roles;
    roles.forEach(rol => {
         if(rol == permissao){
          resultado = true;
         }
    });
    return resultado
  }


}
