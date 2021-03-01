import { DatePipe } from "@angular/common";
import { Sexo } from "../sexo/sexo.model";

export class Cidadao {

     id             : number  = 0;
	 nome           : String  = "";
	 cpfCnpj        : String  = "";
	 email          : String  = "";
	 telefone       : String  = "";
	 //dataNascimento : DatePipe  ;
	 endereco       : String  = "";
	 cep            : String  = "";
	 municipioId    : String  =  "";
	 numero         : String  = "";
	 sexo           : Sexo = new Sexo();
	 //TipoPessoa: tipoPessoa;


}