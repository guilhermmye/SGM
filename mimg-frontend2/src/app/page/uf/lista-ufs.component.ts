import { Component, OnInit } from '@angular/core';
import { UfService } from 'src/app/share/service/uf/uf.service';
import { Regiao } from 'src/app/shared/model/regiao/regiao.model';
import { Uf } from 'src/app/shared/model/uf/uf.model';


@Component({
  selector: 'app-lista-ufs',
  templateUrl: './lista-ufs.component.html',
  styleUrls: ['./lista-ufs.component.css']

  
})
export class ListaUfsComponent implements  OnInit {

  ufs: Uf[] = [];
    displayedColumns = ['nome','sigla','regiao']
  constructor(
    public ufService:UfService
  ) { }

  ngOnInit(): void {
    this.getListar()
  }

  getListar(){
    this.ufService.getufs("ufs").subscribe(data => {
       this.ufs = data.content;
       console.log(this.ufs);
    });
}




}
