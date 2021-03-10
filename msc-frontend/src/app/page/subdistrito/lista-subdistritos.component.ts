import { Component, OnInit } from '@angular/core';
import { SubdistritoService } from 'src/app/share/service/subdistrito/subdistrito.service';
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
    this.getListar()
  }

  getListar(){
    this.subdistritoService.getSubdistritos("subdistritos").subscribe(data => {
       this.subdistritos = data.content;
       console.log(this.subdistritos);
    });
}




}
