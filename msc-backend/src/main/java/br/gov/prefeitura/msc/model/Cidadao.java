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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private Integer ufId;
	private String  numero;
	private Sexo    sexo;
	private TipoPessoa tipoPessoa;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cidadao_id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "uf_id")
	public Integer getUfId() {
		return ufId;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Date getDataNascimento() {
		return dataNascimento;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sexo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Sexo getSexo()
    {
        return sexo;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoPessoa_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public TipoPessoa getTipoPessoa()
    {
        return tipoPessoa;
    }
}
