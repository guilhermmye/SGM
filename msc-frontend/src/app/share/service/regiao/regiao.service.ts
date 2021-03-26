import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/regiao/responsePageable.model';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../login/token-storage.service';


@Injectable({
  providedIn: 'root'
})
export class RegiaoService {

  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

  public getRegioes(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })
    });
  }

  public getImportarRegioes(): Observable<ResponsePageable> {    
   return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes/ibge/1|2|3|4|5`,{
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+this.tokenStorage.getToken()
    })
   })
 }
 public getImportarTudo(): Observable<ResponsePageable> {   
  return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/regioes/importar/1|2|3|4|5`,{
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+this.tokenStorage.getToken()
    })
  })
}


}
