package br.gov.prefeitura.stur.repository.cidadao.filter;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SturFilter {
	
	private Integer    id;
	private String     inscricao;
	private String     tipoImposto;
	private BigDecimal parcela;
	private BigDecimal valorTotal;
	private String     cpfCnpj;
	private String     endereco;
	private String     cep;
	private String     municipio;
	private String     uf;
	private String     numero;
}
