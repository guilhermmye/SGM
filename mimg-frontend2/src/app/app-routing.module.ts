import { Routes } from "@angular/router"
import { HomeComponent } from './page/home/home.component';
import { ListaDistritosComponent } from './page/distrito/lista-distritos.component';
import { ListaMesorregioesComponent } from './page/mesorregiao/lista-mesorregioes.component';
import { ListaMicrorregioesComponent } from './page/microrregiao/lista-microrregioes.component';
import { ListaMunicipiosComponent } from './page/municipio/lista-municipios.component';
import { ListaRegioesComponent } from './page/regiao/lista-regioes.component';
import { ListaRegioesImediatasComponent } from './page/regiaoImediata/lista-regioesImediatas.component';
import { ListaRegioesIntermediariasComponent } from './page/regiaoIntermediaria/lista-regioesIntermediarias.component';
import { ListaSubdistritosComponent } from './page/subdistrito/lista-subdistritos.component';
import { ListaUfsComponent } from './page/uf/lista-ufs.component';
import { TableComponent } from './page/table/table.component';
 


export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'table', component: TableComponent}, 
  {path: 'distrito', component: ListaDistritosComponent},
  {path: 'mesorregiao', component: ListaMesorregioesComponent},
  {path: 'microrregiao', component: ListaMicrorregioesComponent},
  {path: 'municipio', component: ListaMunicipiosComponent},
  {path: 'regiao', component: ListaRegioesComponent},
  {path: 'regiaoImediata', component: ListaRegioesImediatasComponent},
  {path: 'regiaoIntermediaria', component: ListaRegioesIntermediariasComponent},
  {path: 'subdistrito', component: ListaSubdistritosComponent},
  {path: 'uf', component: ListaUfsComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'},
];

