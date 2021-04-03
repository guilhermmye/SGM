import { Component, OnInit } from '@angular/core';
import { RegiaoService } from 'src/app/share/service/georreferenciamento/apoio/regiao/regiao.service';
import { ImportarService } from 'src/app/share/service/georreferenciamento/apoio/importar/importar.service';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';
import { Router } from '@angular/router';
import {MatTabsModule} from '@angular/material/tabs'
import { NotificacaoService } from 'src/app/notificacao.service';


@Component({
  selector: 'app-importar',
  templateUrl: './importar.component.html',
  styleUrls: ['./importar.component.css']

  
})
export class ImportarComponent implements  OnInit {
desabilitarImportacao:      boolean = false;
possuiRegiao:               boolean = false;
possuiUf:                   boolean = false;
possuiMesorregiao:          boolean = false;
possuiMicrorregiao:         boolean = false;
possuiRegiaoIntermediaria:  boolean = false;
possuiRegiaoImediata:       boolean = false;
possuiMunicipio:            boolean = false;

constructor(public importarService:ImportarService, private router: Router,private notificacaoService:NotificacaoService) { }

ngOnInit(){

}

importarTudo(){
  this.importarService.importarTudo()   
  .toPromise()
  .then((resposta) => {
  var ok = resposta;
  this.notificacaoService.showNotificationNotButton('Importação realizada com sucesso !','sucesso');   
  this.reloadPage();
}).catch((erro) => {
  var erros = erro;
  this.notificacaoService.showNotificationNotButton(erro,'erro');
}); 
}

desabilitar(desabilitar:boolean){
 if(desabilitar){
  this.desabilitarImportacao = desabilitar
 }
}


isRegiao(possuiRegiao:boolean) {
  this.possuiRegiao = possuiRegiao;
  this.desabilitar(possuiRegiao)
}

isUf(possuiUf:boolean) {
  this.possuiUf = possuiUf;
  this.desabilitar(possuiUf)
}

isMesorregiao(possuiMesorregiao:boolean) {
  this.possuiMesorregiao = possuiMesorregiao;
  this.desabilitar(possuiMesorregiao)
}

isMicrorregiao(possuiMicrorregiao:boolean) {
  this.possuiMicrorregiao = possuiMicrorregiao;
  this.desabilitar(possuiMicrorregiao)
}

isRegiaoIntermediaria(possuiRegiaoIntermediaria:boolean) {
  this.possuiRegiaoIntermediaria = possuiRegiaoIntermediaria;
  this.desabilitar(possuiRegiaoIntermediaria)
}

isRegiaoImediata(possuiRegiaoImediata:boolean) {
  this.possuiRegiaoImediata = possuiRegiaoImediata;
  this.desabilitar(possuiRegiaoImediata)
}

isMunicipio(possuiMunicipio:boolean) {
  this.possuiMunicipio = possuiMunicipio;
  this.desabilitar(possuiMunicipio)
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

reloadPage(): void {
  window.location.reload();
}

}
