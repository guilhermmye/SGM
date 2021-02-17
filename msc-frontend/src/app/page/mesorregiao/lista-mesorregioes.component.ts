import { Component, OnInit } from '@angular/core';
import { MesorregiaoService } from 'src/app/share/service/mesorregiao/mesorregiao.service';
import { Mesorregiao } from 'src/app/shared/model/mesorregiao/mesorregiao.model';


@Component({
  selector: 'app-lista-mesorregioes',
  templateUrl: './lista-mesorregioes.component.html',
  styleUrls: ['./lista-mesorregioes.component.css']

  
})
export class ListaMesorregioesComponent implements  OnInit {

  mesorregioes: Mesorregiao[] = [];
  displayedColumns = ['nome','uf']
  constructor(
    public mesorregiaoService:MesorregiaoService
  ) { }

  ngOnInit(): void {
    this.getListar()
  }

  getListar(){
    this.mesorregiaoService.getMesorregioes("mesorregioes").subscribe(data => {
       this.mesorregioes = data.content;
       console.log(this.mesorregioes);
    });
}




}
