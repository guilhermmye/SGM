package br.gov.prefeitura.seguranca.repository.usuario.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFilter {
	
	private String  nome;
	private String  cpfCnpj;
	private String  email;
}
