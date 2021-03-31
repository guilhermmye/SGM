import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiaoIntermediaria/responsePageable.model';
import { RegiaoIntermediaria } from 'src/app/shared/model/regiaoIntermediaria/regiaoIntermediaria.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RegiaoIntermediariaService {

constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

public listarRegioesIntermediarias(): Observable<ResponsePageable> {
  return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioesIntermediarias`,{
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+this.tokenStorage.getToken()
    })
  });
}

}
