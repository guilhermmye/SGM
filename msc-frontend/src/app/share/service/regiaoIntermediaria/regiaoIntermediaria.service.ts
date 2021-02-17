import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiaoIntermediaria/responsePageable.model';
import { RegiaoIntermediaria } from 'src/app/shared/model/regiaoIntermediaria/regiaoIntermediaria.model';

@Injectable({
  providedIn: 'root'
})
export class RegiaoIntermediariaService {

apiUrl = 'http://localhost:8080/regioesIntermediarias';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}

   public getRegioesIntermediarias(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
