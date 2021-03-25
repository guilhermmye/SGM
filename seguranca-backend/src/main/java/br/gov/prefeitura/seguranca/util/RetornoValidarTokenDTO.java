package br.gov.prefeitura.seguranca.util;

import br.gov.prefeitura.seguranca.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoValidarTokenDTO {
	private Boolean valido;   
	private Usuario usuario; 	
}
