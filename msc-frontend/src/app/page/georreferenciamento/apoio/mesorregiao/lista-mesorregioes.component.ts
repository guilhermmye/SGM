import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
import { MesorregiaoService } from 'src/app/share/service/georreferenciamento/apoio/mesorregiao/mesorregiao.service';
import { Mesorregiao } from 'src/app/shared/model/mesorregiao/mesorregiao.model';


@Component({
  selector: 'app-lista-mesorregioes',
  templateUrl: './lista-mesorregioes.component.html',
  styleUrls: ['./lista-mesorregioes.component.css']

  
})
export class ListaMesorregioesComponent implements  OnInit {

  mesorregioes: Mesorregiao[] = [];
  displayedColumns = ['nome','uf']
  @Output() possuiMesorregiao = new EventEmitter();

  constructor(
    public mesorregiaoService:MesorregiaoService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.mesorregiaoService.listarMesorregioes()
    .toPromise()
    .then((resposta) => {
      this.mesorregioes = resposta.content; 
      this.possuiMesorregiao.emit(this.mesorregioes.length > 0 ? true : false);
      var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  });  
}

}
