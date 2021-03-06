package br.gov.prefeitura.mimg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="uf")
public class Uf {
	
	private Integer id;
	private String nome;
	private String sigla;
	private Regiao regiao;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uf_id")
	public Integer getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regiao_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Regiao getRegiao()
    {
        return regiao;
    }
	
}
