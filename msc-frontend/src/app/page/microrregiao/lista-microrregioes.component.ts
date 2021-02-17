import { Component, OnInit } from '@angular/core';
import { MicrorregiaoService } from 'src/app/share/service/microrregiao/microrregiao.service';
import { Microrregiao } from 'src/app/shared/model/microrregiao/microrregiao.model';


@Component({
  selector: 'app-lista-microrregioes',
  templateUrl: './lista-microrregioes.component.html',
  styleUrls: ['./lista-microrregioes.component.css']

  
})
export class ListaMicrorregioesComponent implements  OnInit {

  microrregioes: Microrregiao[] = [];
  displayedColumns = ['nome','mesorregiao']
  constructor(
    public microrregiaoService:MicrorregiaoService
  ) { }

  ngOnInit(): void {
    this.getListar()
  }

  getListar(){
    this.microrregiaoService.getMicrorregioes("microrregioes").subscribe(data => {
       this.microrregioes = data.content;
       console.log(this.microrregioes);
    });
}




}
