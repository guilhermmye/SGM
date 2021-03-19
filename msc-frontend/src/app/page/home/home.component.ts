import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router) { 

  } 
  
btnPesquisarCidadao() {
  this.router.navigateByUrl('/pesquisarCidadao');
};

btnPesquisarRegioes() {
  this.router.navigateByUrl('/regiao');
};

btnPermissaoAcesso() {
  this.router.navigateByUrl('/permissaoAcesso');
};

  ngOnInit() {
  }
 
}


