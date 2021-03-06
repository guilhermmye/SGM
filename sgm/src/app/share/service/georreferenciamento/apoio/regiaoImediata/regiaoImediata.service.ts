import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiaoImediata/responsePageable.model';
import { RegiaoImediata } from 'src/app/shared/model/regiaoImediata/regiaoImediata.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RegiaoImediataService {

constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

public listarRegioesImediatas(): Observable<ResponsePageable> {   
  return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioesImediatas`,{
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'x-wso2-auth-header': 'Bearer '+this.tokenStorage.getToken()
    })
  })
}

}
