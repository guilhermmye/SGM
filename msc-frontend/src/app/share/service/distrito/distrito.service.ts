import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/distrito/responsePageable.model';
import { Distrito } from 'src/app/shared/model/distrito/distrito.model';

@Injectable({
  providedIn: 'root'
})
export class DistritoService {

apiUrl = 'http://localhost:8080/distritos';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}

   public getDistritos(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
