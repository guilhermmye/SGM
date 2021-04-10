import { Component, OnInit,Output, EventEmitter } from '@angular/core';
import { RegiaoImediataService } from 'src/app/share/service/georreferenciamento/apoio/regiaoImediata/regiaoImediata.service';
import { RegiaoImediata } from 'src/app/shared/model/regiaoImediata/regiaoImediata.model';


@Component({
  selector: 'app-lista-regioesImediatas',
  templateUrl: './lista-regioesImediatas.component.html',
  styleUrls: ['./lista-regioesImediatas.component.css']

  
})
export class ListaRegioesImediatasComponent implements  OnInit {

  regioesImediatas: RegiaoImediata[] = [];
  displayedColumns = ['nome','regiaoIntermediaria']
  @Output() possuiRegiaoImediata = new EventEmitter();
  constructor(
    public regiaoImediataService:RegiaoImediataService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.regiaoImediataService.listarRegioesImediatas()
    .toPromise()
    .then((resposta) => {
      this.regioesImediatas = resposta.content; 
      this.possuiRegiaoImediata.emit(this.regioesImediatas.length > 0 ? true : false);
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  })
}

}
