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

  displayedColumns:string[] =['cpfCnpj','nome','tipoPessoa','opcoes'];
  dataSource:any;
  
  profileForm : FormGroup = this.iniciarForm();

  constructor(public CidadaoService: CidadaoService,private router: Router) {
    
  }

  iniciarForm(){
  return this.profileForm = new FormGroup({  
      nome              :  new FormControl(this.cidadao.nome),
      cpfCnpj           :  new FormControl(this.cidadao.cpfCnpj)
  });
}
  ngOnInit() {
    this.cidadao = new Cidadao();
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
    }).catch((erro) => {
      var erros = erro;
    }); 
}

excluir(id:any){
  this.CidadaoService.excluirCidadao(id)
  .toPromise()
  .then((ok) => {

}).catch((erro) => {
  var erros = erro;
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

}
