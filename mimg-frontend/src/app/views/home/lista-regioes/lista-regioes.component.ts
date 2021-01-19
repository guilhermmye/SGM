import { Component, OnInit } from '@angular/core';
import { RegiaoService } from 'src/app/share/service/regiao.service';
import { Regiao } from 'src/app/shared/model/regiao.model';



@Component({
  selector: 'app-lista-regioes',
  templateUrl: './lista-regioes.component.html',
  styleUrls: ['./lista-regioes.component.css']

  
})
export class ListaRegioesComponent implements OnInit {

  regioes: Regiao[] = [];

  constructor(
    public regiaoService:RegiaoService
  ) { }

  ngOnInit(): void {
    this.getListar()
  }

  getListar(){
    this.regiaoService.getRegioes("regioes").subscribe(data => {
       this.regioes = data.content;
       console.log(this.regioes);
    });
}




}
