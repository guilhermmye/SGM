import { Component, OnInit ,Output, EventEmitter} from '@angular/core';
import { NotificacaoService } from 'src/app/notificacao.service';
import { DistritoService } from 'src/app/share/service/georreferenciamento/apoio/distrito/distrito.service';
import { Distrito } from 'src/app/shared/model/distrito/distrito.model';


@Component({
  selector: 'app-lista-distritos',
  templateUrl: './lista-distritos.component.html',
  styleUrls: ['./lista-distritos.component.css']

  
})
export class ListaDistritosComponent implements  OnInit {

  distritos: Distrito[] = [];
  displayedColumns = ['nome','municipio']
  @Output() possuiDistrito = new EventEmitter();

  constructor(public distritoService:DistritoService) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.distritoService.listarDistritos()
    .toPromise()
    .then((resposta) => {
      this.distritos = resposta.content; 
      this.possuiDistrito.emit(this.distritos.length > 0 ? true : false);
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  });  
}




}
