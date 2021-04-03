import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/uf/responsePageable.model';
import { Uf } from 'src/app/shared/model/uf/uf.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UfService {


  constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

  public getufs(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/ufs`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })         
    })
  }

  public listar(): Observable<Array<Uf>> {
    return this.httpClient.get<Array<Uf>>(`${environment.mimg_api}/ufs/listar`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })         
    });
  }

}
