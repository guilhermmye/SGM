import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/uf/responsePageable.model';
import { Uf } from 'src/app/shared/model/uf/uf.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../login/token-storage.service';

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

  public listar() {
    return this.httpClient.get(`${environment.mimg_api}/ufs/listar`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })         
    });
  }

  public getImportarUfs(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/ufs/ibge/11|12|13|14|15|16|17|21|22|23|24|25|26|27|28|29|31|32|33|35|41|42|43|50|51|52|53`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer '+this.tokenStorage.getToken()
      })         
    });
  }

  
  


}
