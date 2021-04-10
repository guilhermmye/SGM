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
import { CadastrarCidadaoComponent } from './page/cidadao/administrar/cadastrar/cadastrarCidadao.component';
import { PesquisarCidadaoComponent } from './page/cidadao/administrar/pesquisar/pesquisarCidadao.component';
import { RouterModule } from '@angular/router';
import {routes} from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import { LOCALE_ID } from '@angular/core';
import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from '@angular/common';
import { NgxMaskModule, IConfig } from 'ngx-mask'
import { ListaRegioesComponent } from './page/georreferenciamento/apoio/regiao/lista-regioes.component';
import { LoginComponent } from './page/login/login.component';
import { AuthGuard } from './share/service/login/auth.guard';
import { AuthService } from './share/service/login/auth.service';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { TokenStorageService } from './share/service/login/token-storage.service';
import { CadastrarPermissaoComponent } from './page/permissaoAcesso/cadastrar/cadastrarPermissao.component';
import { NotificacaoComponent } from './notificacao/notificacao.component';
import { ImportarComponent } from './page/georreferenciamento/apoio/importar/importar.component';
import { ListaMesorregioesComponent } from './page/georreferenciamento/apoio/mesorregiao/lista-mesorregioes.component';
import { ListaMicrorregioesComponent } from './page/georreferenciamento/apoio/microrregiao/lista-microrregioes.component';
import { ListaRegioesIntermediariasComponent } from './page/georreferenciamento/apoio/regiaoIntermediaria/lista-regioesIntermediarias.component';
import { ListaRegioesImediatasComponent } from './page/georreferenciamento/apoio/regiaoImediata/lista-regioesImediatas.component';
import { ListaMunicipiosComponent } from './page/georreferenciamento/apoio/municipio/lista-municipios.component';
import { ListaUfsComponent } from './page/georreferenciamento/apoio/uf/lista-ufs.component';
import { HomeCidadaoComponent } from './page/cidadao/home/homeCidadao.component';
import { HomeGeorreferenciaComponent } from './page/georreferenciamento/home/homeGeorreferenciamento.component';
import { MatTabsModule } from '@angular/material/tabs';

registerLocaleData(localePt, 'pt-BR');

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    ToggleDirective,
    HomeComponent,
    TableComponent,
    CadastrarCidadaoComponent,
    PesquisarCidadaoComponent,
    ListaRegioesComponent,
    LoginComponent,
    CadastrarPermissaoComponent,
    NotificacaoComponent,
    ImportarComponent,
    ListaRegioesComponent,
    ListaUfsComponent,
    ListaMesorregioesComponent,
    ListaMicrorregioesComponent,
    ListaRegioesIntermediariasComponent,
    ListaRegioesImediatasComponent,
    ListaMunicipiosComponent,
    HomeCidadaoComponent,
    HomeGeorreferenciaComponent, 
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
    MatSelectModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatTabsModule,
    NgxMaskModule.forRoot(),
    RouterModule.forRoot(routes)
  ],
  providers: [AuthGuard,AuthService,TokenStorageService,{
    provide: LOCALE_ID, useValue: 'pt-BR',
    useClass: HashLocationStrategy
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
