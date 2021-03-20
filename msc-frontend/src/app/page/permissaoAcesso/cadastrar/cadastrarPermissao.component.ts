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
  displayedColumns:string[] =['nome','email','permissao','opcoes'];
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
      username              :  new FormControl(this.usuario.username,Validators.required),
      password           :  new FormControl({ value:this.usuario.password, disabled:this.isEdicao()},Validators.required),
      email             :  new FormControl(this.usuario.email,Validators.required),
      roles             :  new FormControl(this.usuario.roles,Validators.required),
  });
}

  isEdicao(){
    return this.usuario.id > 0 && this.usuario.id != null;
  }

  ngOnInit() {
    this.usuario = new Usuario();
    this.listarUsuarios();
    this.listarRoles();
  }

  onSubmit(){
    if(!this.profileForm.invalid){
      this.cadastrar(this.profileForm.getRawValue());
    }
  }

  cadastrar(value:any){
    if(value.id > 0 && value.id != null){
      value.roles=[value.roles];
      this.permissaoAcessoService.alterar(value)
        .toPromise()
        .then((resposta) => {
        var ok = resposta;
        this.retornoCallback();
      }).catch((erro) => {
        var erros = erro;
      });
    }else{
        this.permissaoAcessoService.cadastrar(value)
        .toPromise()
        .then((resposta) => {
        var ok = resposta;
        this.retornoCallback();
      }).catch((erro) => {
        var erros = erro;
      }); 
    }   
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

  listarUsuarios(){
    this.permissaoAcessoService.listarUsuarios()
    .toPromise()
    .then((usuarios) => {
      var lista :any;
      lista = usuarios;
      this.dataSource = lista;
    }).catch((erro) => {
      var erros = erro;
    });
  }
    
  carregarTela(){
    this.profileForm.patchValue({
      nome:'',

    })
  }

  private retornoCallback() {
    this.usuario = new Usuario();
    this.iniciarForm();
    this.listarUsuarios; 
    this.reloadPage();
  }

  reloadPage(): void {
    window.location.reload();
  }

  carregarUsuario(value:any) {
    this.usuario.id = value.id;
    this.usuario.username = value.username;
    this.usuario.password = value.password;
    this.usuario.email = value.email;
    this.usuario.roles = value.roles[0];

    this.iniciarForm();
    this.profileForm.setValue(this.usuario); 
   };

  btnPesquisarCidadao() {
    this.router.navigateByUrl('/pesquisarCidadao');
};


}
