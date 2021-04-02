import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiao/responsePageable.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ImportarService {

 constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

  public importarTudo() {
    return this.httpClient.get(`${environment.mimg_api}/importar/tudo`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })
    });
  }


}
