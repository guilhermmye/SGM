import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/municipio/responsePageable.model';
import { Municipio } from 'src/app/shared/model/municipio/municipio.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MunicipioService {

apiUrl = 'http://localhost:8080/municipios';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}

  public getMunicipios(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/municipios`);
  }
  

  public getImportarMunicipios(): Observable<ResponsePageable> {
    
   return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/municipios/ibge/3101|3102|3103|3104|3105|3106|3107|3108|3109|3110|3111|3112-municipios`);
 }



}
