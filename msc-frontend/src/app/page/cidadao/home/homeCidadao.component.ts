import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/share/service/login/token-storage.service';

@Component({
  selector: 'app-homeCidadao',
  templateUrl: './homeCidadao.component.html',
  styleUrls: ['./homeCidadao.component.scss']
})

export class HomeCidadaoComponent implements OnInit {

  isAdministrador:boolean = false
  isTecnico:boolean = false
  isUsuario:boolean = false

  constructor(private router: Router,private tokenStorage: TokenStorageService) {
    
  }

  ngOnInit() {
    this.isAdministrador = this.tokenStorage.permissaoAdm();
    this.isTecnico = this.tokenStorage.permissaoTecnico();
    this.isUsuario = this.tokenStorage.permissaoAdm();
  }

  btnAdministrar() {
      this.router.navigateByUrl('/pesquisarCidadao');
  };

  permissaoAdministrar(){
    return this.isAdministrador || this.isTecnico;
  }

}
