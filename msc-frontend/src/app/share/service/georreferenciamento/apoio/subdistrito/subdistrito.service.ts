import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponsePageable } from 'src/app/shared//model/subdistrito/responsePageable.model';
import { Subdistrito } from 'src/app/shared/model/subdistrito/subdistrito.model';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from '../../../login/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class SubdistritoService {

constructor(private httpClient: HttpClient,private tokenStorage: TokenStorageService) {}

public listarSubdistritos(): Observable<ResponsePageable> {
  return this.httpClient.get<ResponsePageable>(`${environment.mimg_api}/subdistritos`,{
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer '+this.tokenStorage.getToken()
    })
  });
}

}
