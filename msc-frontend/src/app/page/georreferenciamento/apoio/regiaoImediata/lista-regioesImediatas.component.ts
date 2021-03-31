import { Component, OnInit } from '@angular/core';
import { RegiaoImediataService } from 'src/app/share/service/georreferenciamento/apoio/regiaoImediata/regiaoImediata.service';
import { RegiaoImediata } from 'src/app/shared/model/regiaoImediata/regiaoImediata.model';


@Component({
  selector: 'app-lista-regioesImediatas',
  templateUrl: './lista-regioesImediatas.component.html',
  styleUrls: ['./lista-regioesImediatas.component.css']

  
})
export class ListaRegioesImediatasComponent implements  OnInit {

  regioesImediatas: RegiaoImediata[] = [];
  displayedColumns = ['nome','regiaoIntermediaria']
  constructor(
    public regiaoImediataService:RegiaoImediataService
  ) { }

  ngOnInit(): void {
    this.listar()
  }

  listar(){
    this.regiaoImediataService.listarRegioesImediatas().subscribe(data => {
       this.regioesImediatas = data.content;
       console.log(this.regioesImediatas);
    });
}

}
