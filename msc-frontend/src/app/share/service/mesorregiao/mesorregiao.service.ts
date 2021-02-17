import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/mesorregiao/responsePageable.model';
import { Mesorregiao } from 'src/app/shared/model/mesorregiao/mesorregiao.model';

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
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
