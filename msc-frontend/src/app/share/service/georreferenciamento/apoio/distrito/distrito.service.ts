import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/distrito/responsePageable.model';
import { Distrito } from 'src/app/shared/model/distrito/distrito.model';
import { TokenStorageService } from '../../../login/token-storage.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DistritoService {

constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

public listarDistritos(): Observable<ResponsePageable> {
  return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/distritos`,{
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+this.tokenStorage.getToken()
    })
  });
}

}
