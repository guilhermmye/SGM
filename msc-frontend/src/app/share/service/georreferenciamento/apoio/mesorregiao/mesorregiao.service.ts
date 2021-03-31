import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/mesorregiao/responsePageable.model';
import { Mesorregiao } from 'src/app/shared/model/mesorregiao/mesorregiao.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class MesorregiaoService {

  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

  public listarMesorregioes(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/mesorregioes`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })
    });
  }

}
