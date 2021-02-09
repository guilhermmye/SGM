import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/microrregiao/responsePageable.model';
import { Microrregiao } from 'src/app/shared/model/microrregiao/microrregiao.model';

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

   public getMicrorregioes(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
