import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/uf/responsePageable.model';
import { Uf } from 'src/app/shared/model/uf/uf.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UfService {

  apiUrl = 'http://localhost:8080/ufs';

httpOptions={
  
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

  constructor(private httpClient: HttpClient) {}

  public getufs(flag: string): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/ufs`);
  }

  public getImportarUfs(): Observable<ResponsePageable> {
    return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/ufs/ibge/11|12|13|14|15|16|17|21|22|23|24|25|26|27|28|29|31|32|33|35|41|42|43|50|51|52|53`);
  }

  
  


}
