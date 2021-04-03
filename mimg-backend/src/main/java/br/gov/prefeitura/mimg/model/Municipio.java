package br.gov.prefeitura.mimg.model;

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
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="municipio")
public class Municipio {
	
	private Integer id;
	private String nome;
	private RegiaoImediata regiaoImediata;
	private Microrregiao microrregiao;	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "municipio_id")
	public Integer getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regiaoImediata_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonProperty("regiao-imediata")
    public RegiaoImediata getRegiaoImediata()
    {
        return regiaoImediata;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "microrregiao_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Microrregiao getMicrorregiao()
    {
        return microrregiao;
    }
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonProperty("regiaoImediata")
	@Transient
    public RegiaoImediata getRegiaoImediataJson()
    {
        return regiaoImediata;
    }

}
