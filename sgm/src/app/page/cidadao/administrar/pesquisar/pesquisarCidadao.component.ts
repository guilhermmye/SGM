import { Component, OnInit } from '@angular/core';
import { CidadaoService } from 'src/app/share/service/cidadao/cidadao.service';
import { FormGroup, FormControl ,Validators,FormBuilder} from '@angular/forms';
import { Breakpoints } from '@angular/cdk/layout';
import {Cidadao} from 'src/app/shared/model/cidadao/cidadao.model';
import { NgxMaskModule, IConfig } from 'ngx-mask'
import { Sexo } from 'src/app/shared/model/sexo/sexo.model';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/share/service/login/token-storage.service';
import { NotificacaoService } from 'src/app/notificacao.service';

@Component({
  selector: 'app-pesquisarCidadao',
  templateUrl: './pesquisarCidadao.component.html',
  styleUrls: ['./pesquisarCidadao.component.scss']
})

export class PesquisarCidadaoComponent implements OnInit {
  cidadao: Cidadao = new Cidadao();
  isAdministrador:boolean = false
  isTecnico:boolean = false
  isUsuario:boolean = false

  displayedColumns:string[] =['cpfCnpj','nome','tipoPessoa','opcoes'];
  dataSource:any;
  
  profileForm : FormGroup = this.iniciarForm();

  constructor(public CidadaoService: CidadaoService,private notificacaoService:NotificacaoService,private router: Router,private tokenStorage: TokenStorageService) {
    
  }

  iniciarForm(){
  return this.profileForm = new FormGroup({  
      nome              :  new FormControl(this.cidadao.nome),
      cpfCnpj           :  new FormControl(this.cidadao.cpfCnpj)
  });
}
  ngOnInit() {
    this.cidadao = new Cidadao();
    this.isAdministrador = this.tokenStorage.permissaoAdm();
    this.isTecnico = this.tokenStorage.permissaoTecnico();
    this.isUsuario = this.tokenStorage.permissaoAdm();
  }

  onSubmit(){
    if(!this.profileForm.invalid){
     this.pesquisarCidadoes(this.profileForm.getRawValue());
    }
  }

  pesquisarCidadoes(value:any){
      this.CidadaoService.pesquisarCidadoes(value)
      .toPromise()
      .then((cidadaos) => {
        var listaCidadaos :any;
        listaCidadaos = cidadaos;
        this.dataSource = listaCidadaos.content;
        this.notificacaoService.showNotificationNotButton('Pesquisa realizada com sucesso !','sucesso');
    }).catch((erro) => {
      var erros = erro;
      this.notificacaoService.showNotificationNotButton(erro,'erro');
    }); 
}

excluir(id:any){
  this.CidadaoService.excluirCidadao(id)
  .toPromise()
  .then((ok) => {
  this.notificacaoService.showNotificationNotButton('Exclusão realizada com sucesso !','sucesso');
}).catch((erro) => {
  var erros = erro;
  this.notificacaoService.showNotificationNotButton(erro,'erro');
}); 
}

  limparCampos(){
    this.cidadao = new Cidadao();
    this.profileForm.patchValue({
      nome:'',
      cpfCnpj:''
    })
  }

  btnCidadao() {
    this.router.navigateByUrl('/cidadao');
};

btnAlterar(id:any) {
  this.router.navigate(['/cidadao',id]);
};


btnLimpar() {
  this.limparCampos();
};

podeEditar(){
  return this.isAdministrador || this.isTecnico;
}

}
