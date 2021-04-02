package br.gov.prefeitura.mimg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="regiaoimediata")
public class RegiaoImediata implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private RegiaoIntermediaria regiaoIntermediaria;
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "regiaoImediata_id")
	public Integer getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regiaoIntermediaria_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonProperty("regiao-intermediaria")
    public RegiaoIntermediaria getRegiaoIntermediaria()
    {
        return regiaoIntermediaria;
    }
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonProperty("regiaoIntermediaria")
	@Transient
    public RegiaoIntermediaria getRegiaoIntermediariaJson()
    {
        return regiaoIntermediaria;
    }
	
	
}
