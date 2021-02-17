import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/municipio/responsePageable.model';
import { Municipio } from 'src/app/shared/model/municipio/municipio.model';

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

   public getMunicipios(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
