import { Component, OnInit } from '@angular/core';
import { CidadaoService } from 'src/app/share/service/cidadao/cidadao.service';
import { FormGroup, FormControl ,Validators,FormBuilder} from '@angular/forms';
import { Breakpoints } from '@angular/cdk/layout';
import {Usuario} from 'src/app/shared/model/permissaoAcesso/usuario.model';
import { NgxMaskModule, IConfig } from 'ngx-mask'
import { Sexo } from 'src/app/shared/model/sexo/sexo.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PermissaoAcessoService } from 'src/app/share/service/permissaoAcesso/permissaoAcesso.service';

@Component({
  selector: 'app-cadastrarPermissao',
  templateUrl: './cadastrarPermissao.component.html',
  styleUrls: ['./cadastrarPermissao.component.scss']
})

export class CadastrarPermissaoComponent implements OnInit {
  usuario: Usuario = new Usuario();
  displayedColumns:string[] =['nome','email'];
  dataSource:any;
  idUsuario:any;
  profileForm : FormGroup = this.iniciarForm();

  roles:any[] = [];

  constructor(public permissaoAcessoService: PermissaoAcessoService,private router: Router, private activatedRoute :ActivatedRoute) {
    this.idUsuario = this.activatedRoute.snapshot.params.id; 
  }

  iniciarForm(){
  return this.profileForm = new FormGroup({  
      id                : new FormControl({ value: this.usuario.id, disabled: true }, Validators.required),
      nome              :  new FormControl(this.usuario.nome,Validators.required),
      senha           :  new FormControl({ value:this.usuario.senha, disabled:this.isEdicao()},Validators.required),
      email             :  new FormControl(this.usuario.email,Validators.required),
 
  });
}

  isEdicao(){
    return this.usuario.id > 0 && this.usuario.id != null;
  }

  ngOnInit() {
    this.usuario = new Usuario();
    this.listarRoles();
  }

  onSubmit(){
    if(!this.profileForm.invalid){
      this.cadastrar(this.profileForm.getRawValue());
    }
  }

  cadastrar(value:any){

      this.permissaoAcessoService.cadastrar(value)
      .toPromise()
      .then((resposta) => {
      var ok = resposta;
      this.retornoCallback(ok);
    }).catch((erro) => {
      var erros = erro;
    });    

  }
 
  listarRoles(){
    this.permissaoAcessoService.listarRoles()
    .toPromise()
    .then((roles) => {
      var listaRoles :any;
      listaRoles = roles;
      this.roles = listaRoles;
    }).catch((erro) => {
      var erros = erro;
    });
  }
    
  carregarTela(){
    this.profileForm.patchValue({
      nome:'',

    })
  }

  private retornoCallback(r: Usuario) {
    this.usuario = r;
    this.iniciarForm();
    this.profileForm.setValue(r);
    
  }

  btnPesquisarCidadao() {
    this.router.navigateByUrl('/pesquisarCidadao');
};


}
