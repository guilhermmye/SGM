import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/share/service/login/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
 isAdministrador:boolean = false
 isTecnico:boolean = false
 isUsuario:boolean = false

  constructor(private router: Router,private tokenStorage: TokenStorageService) { 
     
  } 
  
btnHomeCidadao() {
  this.router.navigateByUrl('/homeCidadao');
};

btnHomeGeorreferenciamento() {
  this.router.navigateByUrl('/homeGeorreferenciamento');
};

btnPermissaoAcesso() {
  this.router.navigateByUrl('/permissaoAcesso');
};

ngOnInit() {
  this.isAdministrador = this.tokenStorage.permissaoAdm();
  this.isTecnico = this.tokenStorage.permissaoTecnico();
  this.isUsuario = this.tokenStorage.permissaoAdm();
}

permissaoGeorreferencimento(){
  return this.isAdministrador || this.isTecnico;
}

permissaoAcesso(){
  return this.isAdministrador;
}


}


