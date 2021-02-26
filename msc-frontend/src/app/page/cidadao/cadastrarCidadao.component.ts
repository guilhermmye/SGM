import { Component, OnInit } from '@angular/core';
import { CidadaoService } from 'src/app/share/service/cidadao/cidadao.service';
import { FormGroup, FormControl ,Validators,FormBuilder} from '@angular/forms';
import { Breakpoints } from '@angular/cdk/layout';
import {Cidadao} from 'src/app/shared/model/cidadao/cidadao.model';

@Component({
  selector: 'app-cadastrarCidadao',
  templateUrl: './cadastrarCidadao.component.html',
  styleUrls: ['./cadastrarCidadao.component.scss']
})

export class CadastrarCidadaoComponent implements OnInit {
  cidadao: Cidadao = new Cidadao();
  displayedColumns:string[] =['id','nome'];
  dataSource:any;
 
  constructor(public CidadaoService: CidadaoService) {

  }

  profileForm = new FormGroup({  
      id                 : new FormControl({ value: this.cidadao.id, disabled: true }, Validators.required),
      nome              :  new FormControl(this.cidadao.nome,Validators.required),
      cpfCnpj           :  new FormControl(this.cidadao.cpfCnpj,Validators.required),
      email             :  new FormControl(this.cidadao.email,Validators.required),
      telefone          :  new FormControl( this.cidadao.telefone,Validators.required),
      //dataNascimento  :  new FormControl(this.cidadao.id,Validators.required),
      endereco          :  new FormControl(this.cidadao.endereco,Validators.required),
      cep               :  new FormControl(this.cidadao.cep,Validators.required),
      //municipioId     :  new FormControl(this.cidadao.municipioId,Validators.required),
      numero            :  new FormControl(this.cidadao.numero,Validators.required),
      //Sexo            :  new FormControl(this.cidadao.id,Validators.required),
      //TipoPessoa      :  new FormControl(this.cidadao.id,Validators.required),
  });

  ngOnInit() {
    this.cidadao = new Cidadao();
    this.listarCidadaos();
  }

  onSubmit(){
    if(this.profileForm.invalid){
      this.cadastrarProdutos(this.profileForm.getRawValue());
    }
  }

  cadastrarProdutos(value:any){
    if (value.id == undefined) {
      this.CidadaoService.criarCidadao(value)
      .toPromise()
      .then((resposta) => {
        var ok = resposta;
        this.listarCidadaos();
        this. limparCampos();
      }).catch((erro) => {
        var erros = erro;
      });
  }else{
    //PUT
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

  limparCampos(){
    this.profileForm.patchValue({
      nome:'',

    })
  }

  carregarTela(){
    this.profileForm.patchValue({
      nome:'Teste Campo',

    })
  }

}
