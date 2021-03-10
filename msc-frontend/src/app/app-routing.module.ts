import { Routes } from "@angular/router"
import { HomeComponent } from './page/home/home.component';
import { CadastrarCidadaoComponent } from './page/cidadao/cadastrar/cadastrarCidadao.component';
import { PesquisarCidadaoComponent } from './page/cidadao/pesquisar/pesquisarCidadao.component';
import { TableComponent } from './page/table/table.component';
import { ListaRegioesComponent } from "./page/regiao/lista-regioes.component";
 


export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'table', component: TableComponent}, 
  {path: 'cidadao', component: CadastrarCidadaoComponent},
  {path: 'pesquisarCidadao', component: PesquisarCidadaoComponent},
  {path: 'regiao', component: ListaRegioesComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'},
];

