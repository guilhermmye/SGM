import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/cidadao/responsePageable.model';
import { Cidadao } from 'src/app/shared/model/cidadao/cidadao.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../login/token-storage.service';


@Injectable({
  providedIn: 'root'
})
export class CidadaoService {

private readonly urlMsc = environment.msc_api;
private readonly urlStur = environment.stur_api;

  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {
   
  }


  public listarCidadoes(): Observable<ResponsePageable> {
    var url = this.urlMsc+"/cidadaos";
    return this.httpClient.get<ResponsePageable>(url,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })
    })
    
  }

  public obterPorId(id:any) {
    var url = this.urlMsc+"/cidadaos/"+id;
    return this.httpClient.get<Cidadao>(url,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })
    })
    
  }

  public pesquisarCidadoes(cidadao:any): Observable<ResponsePageable> {
  let parametros = new String();
  
  if(cidadao.nome != null && cidadao.nome != '' && (cidadao.cpfCnpj == '' || cidadao.cpfCnpj == null)){
    parametros = "nome="+cidadao.nome;
  }

  if(cidadao.cpfCnpj != null && cidadao.cpfCnpj != '' && (cidadao.nome == '' || cidadao.nome == null)){
    parametros = "cpfCnpj="+cidadao.cpfCnpj;
  }

  if(cidadao.nome != null && cidadao.nome != '' && cidadao.cpfCnpj != '' && cidadao.cpfCnpj != null){
    parametros = "nome="+cidadao.nome+"&cpfCnpj="+cidadao.cpfCnpj;
  }

    var url = this.urlMsc+"/cidadaos?"+parametros;
    return this.httpClient.get<ResponsePageable>(url,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })       
    })  
  }

  public listarSexo() {
    var url = this.urlMsc+"/sexos";
    return this.httpClient.get(url,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })
    })
    
  }

  public criarCidadao(cidadao:any){
    var url = this.urlMsc+"/cidadaos";
    return this.httpClient.post<Cidadao>(url,cidadao,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })
    })
  }

  public alterarCidadao(cidadao:Cidadao){
    var url = this.urlMsc+"/cidadaos/"+cidadao.id;
    return this.httpClient.put<Cidadao>(url,cidadao,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })
    })
  }

  public excluirCidadao(id:any){
    var url = this.urlMsc+"/cidadaos/"+id;
    return this.httpClient.delete(url,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
        })
    })
  }

  public listarStur(cpfCnpj:any) {  
      var url = this.urlStur+"/stur/"+cpfCnpj;
      return this.httpClient.get(url,{
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
          })       
      })  
    }



}
