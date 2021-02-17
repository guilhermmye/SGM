import { Component, OnInit } from '@angular/core';
import { DistritoService } from 'src/app/share/service/distrito/distrito.service';
import { Distrito } from 'src/app/shared/model/distrito/distrito.model';


@Component({
  selector: 'app-lista-distritos',
  templateUrl: './lista-distritos.component.html',
  styleUrls: ['./lista-distritos.component.css']

  
})
export class ListaDistritosComponent implements  OnInit {

  distritos: Distrito[] = [];
  displayedColumns = ['nome','municipio']
  constructor(
    public distritoService:DistritoService
  ) { }

  ngOnInit(): void {
    this.getListar()
  }

  getListar(){
    this.distritoService.getDistritos("distritos").subscribe(data => {
       this.distritos = data.content;
       console.log(this.distritos);
    });
}




}
