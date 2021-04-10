import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
import { MicrorregiaoService } from 'src/app/share/service/georreferenciamento/apoio/microrregiao/microrregiao.service';
import { Microrregiao } from 'src/app/shared/model/microrregiao/microrregiao.model';


@Component({
  selector: 'app-lista-microrregioes',
  templateUrl: './lista-microrregioes.component.html',
  styleUrls: ['./lista-microrregioes.component.css']

  
})
export class ListaMicrorregioesComponent implements  OnInit {

  microrregioes: Microrregiao[] = [];
  displayedColumns = ['nome','mesorregiao']
  @Output() possuiMicrorregiao = new EventEmitter();

  constructor(
    public microrregiaoService:MicrorregiaoService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.microrregiaoService.listarMicrorregioes()
    .toPromise()
    .then((resposta) => {
      this.microrregioes = resposta.content; 
      this.possuiMicrorregiao.emit(this.microrregioes.length > 0 ? true : false);
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  });
}

}
