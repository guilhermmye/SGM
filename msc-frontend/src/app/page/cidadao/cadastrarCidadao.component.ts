import { Component, OnInit } from '@angular/core';
import { CidadaoService } from 'src/app/share/service/cidadao/cidadao.service';
import { FormGroup, FormControl ,Validators,FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-cadastrarCidadao',
  templateUrl: './cadastrarCidadao.component.html',
  styleUrls: ['./cadastrarCidadao.component.scss']
})

export class CadastrarCidadaoComponent implements OnInit {

  displayedColumns:string[] =['id','nome'];
  dataSource:any;

  constructor(public CidadaoService: CidadaoService) {

  }

  profileForm = new FormGroup({
   nome: new FormControl('',Validators.required),

  });

  ngOnInit() {
    this.listarCidadaos();
  }

  onSubmit(){
    var inputNome = this.profileForm.value["nome"];
    this.cadastrarProdutos(inputNome);
  }

  cadastrarProdutos(nome:any){
    var cidadao =
    {
      id:0,
      Nome:nome
    };

    this.CidadaoService.criarCidadao(cidadao)
    .toPromise()
    .then((resposta) => {
      var ok = resposta;
      this.listarCidadaos();
      this. limparCampos();
    }).catch((erro) => {
      var erros = erro;
    });

  }

  listarCidadaos(){
    this.CidadaoService.listarCidadoes()
    .toPromise()
    .then((cidadaos) => {
      var listaCidadaos :any;
      listaCidadaos = cidadaos;
      this.dataSource = listaCidadaos;

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
