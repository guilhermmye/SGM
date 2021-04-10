import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/share/service/login/token-storage.service';

@Component({
  selector: 'app-homeGeorreferenciamento',
  templateUrl: './homeGeorreferenciamento.component.html',
  styleUrls: ['./homeGeorreferenciamento.component.css']

  
})
export class HomeGeorreferenciaComponent implements  OnInit {

isAdministrador:boolean = false
isTecnico:boolean = false
isUsuario:boolean = false

constructor(private router: Router,private tokenStorage: TokenStorageService) { }

ngOnInit(){
  this.isAdministrador = this.tokenStorage.permissaoAdm();
  this.isTecnico = this.tokenStorage.permissaoTecnico();
  this.isUsuario = this.tokenStorage.permissaoAdm();
}

btnImportar() {
  this.router.navigateByUrl('/importar');
};

permissaoImportar(){
return this.isAdministrador || this.isTecnico;
}

}
