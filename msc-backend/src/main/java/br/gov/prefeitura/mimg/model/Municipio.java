package br.gov.prefeitura.mimg.model;

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
@Table(name="municipio_id")
public class Municipio {
	
	private Integer id;
	private String nome;
	private RegiaoIntermediaria regiaoIntermediaria;
	private Microrregiao microrregiao;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "municipio_id")
	public Integer getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regiaoImediata_id")
    public RegiaoIntermediaria getRegiaoIntermediaria()
    {
        return regiaoIntermediaria;
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "microrregiao_id")
    public Microrregiao getMicrorregiao()
    {
        return microrregiao;
    }

}
