import { Component, OnInit } from '@angular/core';
import { CidadaoService } from 'src/app/share/service/cidadao/cidadao.service';
import { FormGroup, FormControl ,Validators,FormBuilder} from '@angular/forms';
import { Breakpoints } from '@angular/cdk/layout';
import {Cidadao} from 'src/app/shared/model/cidadao/cidadao.model';
import { NgxMaskModule, IConfig } from 'ngx-mask'
import { Sexo } from 'src/app/shared/model/sexo/sexo.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pesquisarCidadao',
  templateUrl: './pesquisarCidadao.component.html',
  styleUrls: ['./pesquisarCidadao.component.scss']
})

export class PesquisarCidadaoComponent implements OnInit {
  cidadao: Cidadao = new Cidadao();

  displayedColumns:string[] =['id','nome'];
  dataSource:any;
  
  sexos:any[] = [];

  profileForm : FormGroup = this.iniciarForm();

  constructor(public CidadaoService: CidadaoService,private router: Router) {
  }

  iniciarForm(){
  return this.profileForm = new FormGroup({  
      id                : new FormControl({ value: this.cidadao.id, disabled: true }, Validators.required),
      nome              :  new FormControl(this.cidadao.nome,Validators.required),
      cpfCnpj           :  new FormControl({ value:this.cidadao.cpfCnpj, disabled:this.isEdicao()},Validators.required),
      email             :  new FormControl(this.cidadao.email,Validators.required),
      telefone          :  new FormControl(this.cidadao.telefone,Validators.required),
      dataNascimento    :  new FormControl(this.cidadao.dataNascimento,Validators.required),
      endereco          :  new FormControl(this.cidadao.endereco,Validators.required),
      cep               :  new FormControl(this.cidadao.cep,Validators.required),
      //municipioId     :  new FormControl(this.cidadao.municipioId,Validators.required),
      numero            :  new FormControl(this.cidadao.numero,Validators.required),
      sexo              :  new FormControl(this.cidadao.sexo,Validators.required),      
  });
}

  isEdicao(){
    return this.cidadao.id > 0 && this.cidadao.id != null;
  }

  ngOnInit() {
    this.cidadao = new Cidadao();
    this.listarCidadaos();
    this.listarSexos();

  }

  onSubmit(){
    if(!this.profileForm.invalid){
      this.cadastrarProdutos(this.profileForm.getRawValue());
    }
  }

  cadastrarProdutos(value:any){
    if (value.id > 0 && value.id != null) {
      this.CidadaoService.alterarCidadao(value)
      .toPromise()
      .then((resposta) => {
      var ok = resposta;
      this.listarCidadaos();
      this.limparCampos();
      this.retornoCallback(ok);
    }).catch((erro) => {
      var erros = erro;
    });    
  }else{
    this.CidadaoService.criarCidadao(value)
     .toPromise()
     .then((resposta) => {
      var ok = resposta;
      this.listarCidadaos();
      this.limparCampos();
      this.retornoCallback(ok);
    }).catch((erro) => {
      var erros = erro;
    });
  }

  }

  listarCidadaos(){
    this.CidadaoService.listarCidadoes()
    .toPromise()
    .then((cidadaos) => {
      var listaCidadaos :any;
      listaCidadaos = cidadaos;
      this.dataSource = listaCidadaos.content;
    }).catch((erro) => {
      var erros = erro;
    });
  }

  listarSexos(){
    this.CidadaoService.listarSexo()
    .toPromise()
    .then((sexos) => {
      var listaSexos :any;
      listaSexos = sexos;
      this.sexos = listaSexos;
    }).catch((erro) => {
      var erros = erro;
    });
  }

  limparCampos(){
   // this.cidadao = new Cidadao();
    //this.profileForm.reset();
    //this.iniciarForm();
  }

  carregarTela(){
    this.profileForm.patchValue({
      nome:'',

    })
  }

  private retornoCallback(r: Cidadao) {
    this.cidadao = r;
    this.iniciarForm();
    this.profileForm.setValue(r);
    
  }

  btnCidadao() {
    this.router.navigateByUrl('/cidadao');
};

}
