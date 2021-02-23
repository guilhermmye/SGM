package br.gov.prefeitura.msc.repository.pessoa.filter;

import java.util.Date;

import br.gov.prefeitura.msc.model.Sexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFilter {
	
	private Integer id;
	private String  nome;
	private String  cpfCnpj;
	private String  email;
	private String  telefone;
	private Date    dataNascimento;
	private String  endereco;
	private String  cep;
	private Integer municipioId;
	private String  numero;
	private Sexo    sexo;
	private Integer idTipoPessoa;
}
