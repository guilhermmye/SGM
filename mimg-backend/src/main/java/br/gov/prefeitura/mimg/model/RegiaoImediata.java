package br.gov.prefeitura.mimg.model;

import java.io.Serializable;

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
	@JoinColumn(name = "regiaointermediaria_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public RegiaoIntermediaria getRegiaoIntermediaria()
    {
        return regiaoIntermediaria;
    }
}
