import { Component, OnInit } from '@angular/core';
import { SubdistritoService } from 'src/app/share/service/georreferenciamento/apoio/subdistrito/subdistrito.service';
import { Subdistrito } from 'src/app/shared/model/subdistrito/subdistrito.model';


@Component({
  selector: 'app-lista-subdistritos',
  templateUrl: './lista-subdistritos.component.html',
  styleUrls: ['./lista-subdistritos.component.css']

  
})
export class ListaSubdistritosComponent implements  OnInit {

  subdistritos: Subdistrito[] = [];
  displayedColumns = ['nome','distrito']
  constructor(
    public subdistritoService:SubdistritoService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.subdistritoService.listarSubdistritos()
    .toPromise()
    .then((resposta) => {
      this.subdistritos = resposta.content; 
    var ok = resposta;
  }).catch((erro) => {
    var erros = erro;
  }) 
}

}
