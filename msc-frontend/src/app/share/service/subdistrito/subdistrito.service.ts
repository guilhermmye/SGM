import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/subdistrito/responsePageable.model';
import { Subdistrito } from 'src/app/shared/model/subdistrito/subdistrito.model';

@Injectable({
  providedIn: 'root'
})
export class SubdistritoService {

apiUrl = 'http://localhost:8080/subdistritos';
httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}

   public getSubdistritos(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(this.apiUrl);
}



}
