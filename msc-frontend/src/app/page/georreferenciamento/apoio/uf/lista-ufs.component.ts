import { Component, OnInit } from '@angular/core';
import { UfService } from 'src/app/share/service/georreferenciamento/apoio/uf/uf.service';
import { Uf } from 'src/app/shared/model/uf/uf.model';


@Component({
  selector: 'app-lista-ufs',
  templateUrl: './lista-ufs.component.html',
  styleUrls: ['./lista-ufs.component.css']

  
})
export class ListaUfsComponent implements  OnInit {

  ufs: Uf[] = [];
    displayedColumns = ['nome','sigla','regiao']
  constructor(
    public ufService:UfService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.ufService.getufs()
    .toPromise()
    .then((resposta) => {
      this.ufs = resposta.content; 
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  }) 
}

}
