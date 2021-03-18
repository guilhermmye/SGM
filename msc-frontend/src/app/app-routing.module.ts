import { Routes } from "@angular/router"
import { HomeComponent } from './page/home/home.component';
import { CadastrarCidadaoComponent } from './page/cidadao/cadastrar/cadastrarCidadao.component';
import { PesquisarCidadaoComponent } from './page/cidadao/pesquisar/pesquisarCidadao.component';
import { TableComponent } from './page/table/table.component';
import { ListaRegioesComponent } from "./page/regiao/lista-regioes.component";
import { LoginComponent } from './page/login/login.component';
import { AuthGuard } from "./share/service/login/auth.guard";
 


export const routes: Routes = [
  {path: 'home', component: HomeComponent,canActivate:[AuthGuard]},
  {path: 'table', component: TableComponent,canActivate:[AuthGuard]}, 
  {path: 'cidadao', component: CadastrarCidadaoComponent,canActivate:[AuthGuard]},
  {path: 'cidadao/:id', component: CadastrarCidadaoComponent,canActivate:[AuthGuard]},
  {path: 'pesquisarCidadao', component: PesquisarCidadaoComponent,canActivate:[AuthGuard]},
  {path: 'regiao', component: ListaRegioesComponent,canActivate:[AuthGuard]},
  {path: 'login', component: LoginComponent },
  {path: '', redirectTo: '/home', pathMatch: 'full'},
];

