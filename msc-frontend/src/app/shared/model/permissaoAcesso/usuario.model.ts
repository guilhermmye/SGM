import { DatePipe } from "@angular/common";
import { Sexo } from "../sexo/sexo.model";

export class Usuario {

     id             : number  = 0;
	 username       : String  = "";
	 password       : String  = "";
	 email          : String  = "";
	 roles			: [] = [];
}