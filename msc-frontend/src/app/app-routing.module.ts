import { Routes } from "@angular/router"
import { HomeComponent } from './page/home/home.component';
import { CadastrarCidadaoComponent } from './page/cidadao/cadastrar/cadastrarCidadao.component';
import { PesquisarCidadaoComponent } from './page/cidadao/pesquisar/pesquisarCidadao.component';
import { TableComponent } from './page/table/table.component';
import { ListaRegioesComponent } from "./page/regiao/lista-regioes.component";
import { LoginComponent } from './page/login/login.component';
import { AuthGuard } from "./share/service/login/auth.guard";
import { CadastrarPermissaoComponent } from "./page/permissaoAcesso/cadastrar/cadastrarPermissao.component";
 


export const routes: Routes = [
  {path: 'home', component: HomeComponent,canActivate:[AuthGuard]},
  {path: 'table', component: TableComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}}, 
  {path: 'cidadao', component: CadastrarCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_USUARIO','ROLE_TECNICO']}},
  {path: 'cidadao/:id', component: CadastrarCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_USUARIO','ROLE_TECNICO']}},
  {path: 'pesquisarCidadao', component: PesquisarCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_USUARIO','ROLE_TECNICO']}},
  {path: 'regiao', component: ListaRegioesComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_TECNICO']}},
  {path: 'permissaoAcesso', component: CadastrarPermissaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
];

