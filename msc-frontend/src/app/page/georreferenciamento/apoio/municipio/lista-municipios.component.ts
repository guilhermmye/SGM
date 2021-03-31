import { Component, OnInit } from '@angular/core';
import { MunicipioService } from 'src/app/share/service/georreferenciamento/apoio/municipio/municipio.service';
import { Municipio } from 'src/app/shared/model/municipio/municipio.model';


@Component({
  selector: 'app-lista-municipios',
  templateUrl: './lista-municipios.component.html',
  styleUrls: ['./lista-municipios.component.css']

  
})
export class ListaMunicipiosComponent implements  OnInit {

  municipios: Municipio[] = [];
  displayedColumns = ['nome','regiaoImediata','microrregiao']
  constructor(
    public municipioService:MunicipioService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.municipioService.listarMunicipios().subscribe(data => {
       this.municipios = data.content;
       console.log(this.municipios);
    });
}

}
