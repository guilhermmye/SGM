import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiaoImediata/responsePageable.model';
import { RegiaoImediata } from 'src/app/shared/model/regiaoImediata/regiaoImediata.model';

@Injectable({
  providedIn: 'root'
})
export class RegiaoImediataService {

apiUrl = 'http://localhost:8080/regioesImediatas';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}

   public getRegioesImediatas(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
