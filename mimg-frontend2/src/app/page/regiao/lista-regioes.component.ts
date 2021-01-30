import { Component, OnInit } from '@angular/core';
import { RegiaoService } from 'src/app/share/service/regiao/regiao.service';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';



@Component({
  selector: 'app-lista-regioes',
  templateUrl: './lista-regioes.component.html',
  styleUrls: ['./lista-regioes.component.css']

  
})
export class ListaRegioesComponent implements OnInit {

  regioes: Regiao[] = [];
  displayedColumns = ['nome','sigla']
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
