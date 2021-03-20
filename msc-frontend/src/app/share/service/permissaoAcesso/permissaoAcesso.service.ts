import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../login/token-storage.service';
import { Usuario } from 'src/app/shared/model/permissaoAcesso/usuario.model';

const urlSeguranca = environment.seguranca_api;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PermissaoAcessoService {
  testa: any;

  constructor(private http: HttpClient,private tokenStorage: TokenStorageService) { }

  cadastrar(usuario:any): Observable<any> {
    return this.http.post(urlSeguranca + '/auth/signup', {
      username: usuario.nome,
      email: usuario.email,
      password: usuario.senha,
      role:[usuario.role.name]    
    }, httpOptions);
  }

  public alterar(usuario:Usuario){
    
    return this.http.put<Usuario>(urlSeguranca+'/usuarios/'+usuario.id,usuario,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer '+this.tokenStorage.getToken()
        })
    })
  }

  public listarRoles() {   
    return this.http.get(urlSeguranca+'/roles',{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer '+this.tokenStorage.getToken()
        })
    })   
  }

  public listarUsuarios() {   
    return this.http.get(urlSeguranca+'/usuarios',{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer '+this.tokenStorage.getToken()
        })
    })   
  }
}
