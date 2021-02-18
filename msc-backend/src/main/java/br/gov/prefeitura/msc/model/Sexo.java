package br.gov.prefeitura.msc.model;

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
@Table(name="sexo")
public class Sexo {
	
	private Integer id;
	
	@NotNull
	@Size(min = 3,max = 100)
	private String descricao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sexo_id")
	public Integer getId() {
		return id;
	}
	
	
}
