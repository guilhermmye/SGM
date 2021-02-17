package br.gov.prefeitura.mimg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="regiao")
public class Regiao implements Serializable{
	
	private Integer id;
	
	@NotNull
	@Size(min = 2,max = 100)
	private String nome;
	
	@NotNull
	@Size(min = 2,max = 100)
	private String sigla;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "regiao_id")
	public Integer getId() {
		return id;
	}
}
