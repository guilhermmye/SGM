import { Routes } from "@angular/router"
import { HomeComponent } from './page/home/home.component';
import { ListaRegioesComponent } from './page/regiao/lista-regioes.component';
import { TableComponent } from './page/table/table.component';
 


export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'table', component: TableComponent},
  {path: 'regiao', component: ListaRegioesComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'},
];

