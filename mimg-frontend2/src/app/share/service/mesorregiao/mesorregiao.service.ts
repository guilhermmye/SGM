import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/mesorregiao/responsePageable.model';
import { Mesorregiao } from 'src/app/shared/model/mesorregiao/mesorregiao.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MesorregiaoService {

apiUrl = 'http://localhost:8080/mesorregioes';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}


  public getMesorregioes(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/mesorregioes`);
  }

  public getImportarMesorregioes(): Observable<ResponsePageable> {
    
   return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/mesorregioes/ibge/31|mesorregioes`)
 }




}
