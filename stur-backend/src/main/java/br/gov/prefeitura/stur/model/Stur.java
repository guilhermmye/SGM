package br.gov.prefeitura.stur.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="stur")
public class Stur {
	
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stur_id")
	public Integer getId() {
		return id;
	}
		
}
