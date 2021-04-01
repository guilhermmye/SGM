import { Component, OnInit } from '@angular/core';
import { RegiaoService } from 'src/app/share/service/georreferenciamento/apoio/regiao/regiao.service';
import { ImportarService } from 'src/app/share/service/georreferenciamento/apoio/importar/importar.service';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';
import { Router } from '@angular/router';
import {MatTabsModule} from '@angular/material/tabs'


@Component({
  selector: 'app-importar',
  templateUrl: './importar.component.html',
  styleUrls: ['./importar.component.css']

  
})
export class ImportarComponent implements  OnInit {

  regioes: Regiao[] = [];
  displayedColumns = ['nome','sigla']
  constructor(public importarService:ImportarService, private router: Router) { }

ngOnInit(){

}

importarTudo(){
  this.importarService.importarTudo().subscribe(data => {
     this.regioes = data.content;
     console.log(this.regioes);
  });
}

btnPesquisarRegiao() {
  this.router.navigateByUrl('/pesquisarRegiao');
};

btnPesquisarUf() {
  this.router.navigateByUrl('/pesquisarUf');
};

btnPesquisarMesoRegiao() {
  this.router.navigateByUrl('/pesquisarMesoRegiao');
};

btnPesquisarMicroRegiao() {
  this.router.navigateByUrl('/pesquisarMicroRegiao');
};

btnPesquisarRegiaoIntermediaria() {
  this.router.navigateByUrl('/pesquisarRegiaoIntermediaria');
};

btnPesquisarRegiaoImediata() {
  this.router.navigateByUrl('/pesquisarRegiaoImediata');
};

btnPesquisarMunicipio() {
  this.router.navigateByUrl('/pesquisarMunicipio');
};

}
