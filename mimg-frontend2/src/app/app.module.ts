import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { SidebarComponent } from './dashboard/sidebar/sidebar.component';
import { ToggleDirective } from './dashboard/sidebar/toggle.directive';
import { HomeComponent } from './page/home/home.component';
import { TableComponent } from './page/table/table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { ListaRegioesComponent } from './page/regiao/lista-regioes.component';
import { ListaDistritosComponent } from './page/distrito/lista-distritos.component';
import { ListaMesorregioesComponent } from './page/mesorregiao/lista-mesorregioes.component';
import { ListaMicrorregioesComponent } from './page/microrregiao/lista-microrregioes.component';
import { ListaMunicipiosComponent } from './page/municipio/lista-municipios.component';
import { ListaRegioesImediatasComponent } from './page/regiaoImediata/lista-regioesImediatas.component';
import { ListaRegioesIntermediariasComponent } from './page/regiaoIntermediaria/lista-regioesIntermediarias.component';
import { ListaSubdistritosComponent } from './page/subdistrito/lista-subdistritos.component';
import { ListaUfsComponent } from './page/uf/lista-ufs.component';
import { RouterModule } from '@angular/router';
import {routes} from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';





@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    ToggleDirective,
    HomeComponent,
    TableComponent,
    ListaRegioesComponent,
    ListaDistritosComponent,
    ListaMesorregioesComponent,
    ListaMicrorregioesComponent,
    ListaMunicipiosComponent,
    ListaRegioesImediatasComponent,
    ListaRegioesIntermediariasComponent,
    ListaSubdistritosComponent,
    ListaUfsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatTooltipModule,
    MatRippleModule,
    HttpClientModule, 
    RouterModule.forRoot(routes)
  ],
  providers: [{
    provide: LocationStrategy,
    useClass: HashLocationStrategy
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
