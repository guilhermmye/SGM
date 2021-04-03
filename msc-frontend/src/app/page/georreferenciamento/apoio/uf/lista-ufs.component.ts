import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
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
  @Output() possuiUf = new EventEmitter();

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
      this.possuiUf.emit(this.ufs.length > 0 ? true : false);
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  }) 
}

}
