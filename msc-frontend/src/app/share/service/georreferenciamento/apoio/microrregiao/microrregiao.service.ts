import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/microrregiao/responsePageable.model';
import { Microrregiao } from 'src/app/shared/model/microrregiao/microrregiao.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class MicrorregiaoService {

  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

  public listarMicrorregioes(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/microrregioes`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })
    });
  }
}
