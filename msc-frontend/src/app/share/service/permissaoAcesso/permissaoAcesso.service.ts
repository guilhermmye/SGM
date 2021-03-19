import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const urlSeguranca = environment.seguranca_api;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PermissaoAcessoService {

  constructor(private http: HttpClient) { }

  cadastrar(usuario:any): Observable<any> {
    return this.http.post(urlSeguranca + '/auth/signup', {
      username: usuario.nome,
      email: usuario.email,
      password: usuario.senha,
      role:["admin"]    
    }, httpOptions);
  }

  public listarRoles() {
    return this.http.get(urlSeguranca+'roles',{
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
    })
    
  }
}
