import { Component, OnInit } from '@angular/core';
import { RegiaoIntermediariaService } from 'src/app/share/service/georreferenciamento/apoio/regiaoIntermediaria/regiaoIntermediaria.service';
import { RegiaoIntermediaria } from 'src/app/shared/model/regiaoIntermediaria/regiaoIntermediaria.model';


@Component({
  selector: 'app-lista-regioesIntermediarias',
  templateUrl: './lista-regioesIntermediarias.component.html',
  styleUrls: ['./lista-regioesIntermediarias.component.css']

  
})
export class ListaRegioesIntermediariasComponent implements  OnInit {

  regioesIntermediarias: RegiaoIntermediaria[] = [];
  displayedColumns = ['nome','uf']
  constructor(
    public regiaoIntermediariaService:RegiaoIntermediariaService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

 listar(){
    this.regiaoIntermediariaService.listarRegioesIntermediarias()
    .toPromise()
    .then((resposta) => {
      this.regioesIntermediarias = resposta.content; 
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  }) 
}

}
