import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
import { MunicipioService } from 'src/app/share/service/georreferenciamento/apoio/municipio/municipio.service';
import { Municipio } from 'src/app/shared/model/municipio/municipio.model';


@Component({
  selector: 'app-lista-municipios',
  templateUrl: './lista-municipios.component.html',
  styleUrls: ['./lista-municipios.component.css']

  
})
export class ListaMunicipiosComponent implements  OnInit {

  municipios: Municipio[] = [];
  displayedColumns = ['nome','uf','regiaoImediata','microrregiao']
  @Output() possuiMunicipio = new EventEmitter();

  constructor(
    public municipioService:MunicipioService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.municipioService.listarMunicipios()
    .toPromise()
    .then((resposta) => {
      this.municipios = resposta.content; 
      this.possuiMunicipio.emit(this.municipios.length > 0 ? true : false);
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  })
}

}
