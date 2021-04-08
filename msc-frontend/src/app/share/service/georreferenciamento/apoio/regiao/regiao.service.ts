import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiao/responsePageable.model';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';


@Injectable({
  providedIn: 'root'
})
export class RegiaoService {

  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

  public listarRegioes(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
      })
    });
  }
}
