import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
import { RegiaoService } from 'src/app/share/service/georreferenciamento/apoio/regiao/regiao.service';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';


@Component({
  selector: 'app-lista-regioes',
  templateUrl: './lista-regioes.component.html',
  styleUrls: ['./lista-regioes.component.css']

  
})
export class ListaRegioesComponent implements  OnInit {

  regioes: Regiao[] = [];
  displayedColumns = ['nome','sigla']
  @Output() possuiRegiao = new EventEmitter();

  constructor(
    public regiaoService:RegiaoService
  ) { }

  ngOnInit(): void {
    this.listar() 
  }

  listar(){
    this.regiaoService.listarRegioes()
    .toPromise()
    .then((resposta) => {
      this.regioes = resposta.content; 
      this.possuiRegiao.emit(this.regioes.length > 0 ? true : false);
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  })
}

}
