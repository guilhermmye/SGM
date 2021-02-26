import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/cidadao/responsePageable.model';
import { Cidadao } from 'src/app/shared/model/cidadao/cidadao.model';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class CidadaoService {

private readonly urlMsc = environment.msc_api;

  constructor(private httpClient: HttpClient) {
   
  }


  public listarCidadoes(): Observable<ResponsePageable> {
    var url = this.urlMsc+"/cidadaos";
    return this.httpClient.get<ResponsePageable>(url,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
    })
    
  }

  public criarCidadao(cidadao:any){
    var url = this.urlMsc+"/cidadaos";
    return this.httpClient.post<Cidadao>(url,cidadao,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
    })
  }




}
