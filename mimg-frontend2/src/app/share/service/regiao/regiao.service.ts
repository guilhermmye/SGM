import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiao/responsePageable.model';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class RegiaoService {

apiUrl = 'http://localhost:8080/regioes';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}


  public getRegioes(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes`);
  }

  public getImportarRegioes(): Observable<ResponsePageable> {
    
   return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes/ibge/1|2|3|4|5`)
 }
 public getImportarTudo(): Observable<ResponsePageable> {
    
  return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes/ibge/1|2|3|4|5`)
}


}
