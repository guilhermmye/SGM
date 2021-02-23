package br.gov.prefeitura.msc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cidadao")
public class Cidadao {
	
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
	private TipoPessoa tipoPessoa;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pessoa_id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "municipio_id")
	public Integer getMunicipioId() {
		return municipioId;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sexo_id")
    public Sexo getSexo()
    {
        return sexo;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoPessoa_id")
    public TipoPessoa getTipoPessoa()
    {
        return tipoPessoa;
    }
}
