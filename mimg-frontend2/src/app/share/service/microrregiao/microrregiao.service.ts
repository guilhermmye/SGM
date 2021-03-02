import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/microrregiao/responsePageable.model';
import { Microrregiao } from 'src/app/shared/model/microrregiao/microrregiao.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MicrorregiaoService {

apiUrl = 'http://localhost:8080/microrregioes';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}


  public getMicrorregioes(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/microrregioes`);
  }

  public getImportarMicrorregioes(): Observable<ResponsePageable> {
    
   return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/microrregioes/ibge/3101|3102|3103|3104|3105|3106|3107|3108|3109|3110|3111|3112-microrregioes`);
 }



}
