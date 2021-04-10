import { Routes } from "@angular/router"
import { HomeComponent } from './page/home/home.component';
import { CadastrarCidadaoComponent } from './page/cidadao/administrar/cadastrar/cadastrarCidadao.component';
import { PesquisarCidadaoComponent } from './page/cidadao/administrar/pesquisar/pesquisarCidadao.component';
import { TableComponent } from './page/table/table.component';
import { ListaRegioesComponent } from "./page/georreferenciamento/apoio/regiao/lista-regioes.component";
import { LoginComponent } from './page/login/login.component';
import { AuthGuard } from "./share/service/login/auth.guard";
import { CadastrarPermissaoComponent } from "./page/permissaoAcesso/cadastrar/cadastrarPermissao.component";
import { ListaUfsComponent } from "./page/georreferenciamento/apoio/uf/lista-ufs.component";
import { ListaMesorregioesComponent } from "./page/georreferenciamento/apoio/mesorregiao/lista-mesorregioes.component";
import { ListaMicrorregioesComponent } from "./page/georreferenciamento/apoio/microrregiao/lista-microrregioes.component";
import { ListaRegioesIntermediariasComponent } from "./page/georreferenciamento/apoio/regiaoIntermediaria/lista-regioesIntermediarias.component";
import { ListaRegioesImediatasComponent } from "./page/georreferenciamento/apoio/regiaoImediata/lista-regioesImediatas.component";
import { ListaMunicipiosComponent } from "./page/georreferenciamento/apoio/municipio/lista-municipios.component";
import { ImportarComponent } from "./page/georreferenciamento/apoio/importar/importar.component";
import { HomeCidadaoComponent } from "./page/cidadao/home/homeCidadao.component";
import { HomeGeorreferenciaComponent } from "./page/georreferenciamento/home/homeGeorreferenciamento.component";
 


export const routes: Routes = [
  //HOME
  {path: 'home', component: HomeComponent,canActivate:[AuthGuard]},
  {path: 'table', component: TableComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}}, 
  
  //CIDADAO
  {path: 'homeCidadao', component: HomeCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_USUARIO','ROLE_TECNICO']}},
  {path: 'cidadao', component: CadastrarCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_TECNICO']}},
  {path: 'cidadao/:id', component: CadastrarCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_TECNICO']}},
  {path: 'pesquisarCidadao', component: PesquisarCidadaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_TECNICO']}},
  
  //GEORREFERENCIAMENTO
  {path: 'homeGeorreferenciamento', component: HomeGeorreferenciaComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN','ROLE_TECNICO']}},
  {path: 'importar', component: ImportarComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarRegiao', component: ListaRegioesComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarUf', component: ListaUfsComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarMesoRegiao', component: ListaMesorregioesComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarMicroRegiao', component: ListaMicrorregioesComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarRegiaoIntermediaria', component: ListaRegioesIntermediariasComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarRegiaoImediata', component: ListaRegioesImediatasComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'pesquisarMunicipio', component: ListaMunicipiosComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},

  //PERMISSAO DE ACESSO
  {path: 'permissaoAcesso', component: CadastrarPermissaoComponent,canActivate:[AuthGuard],data:{allowedRoles:['ROLE_ADMIN']}},
  {path: 'login', component: LoginComponent},

  {path: '', redirectTo: '/home', pathMatch: 'full'},
];

