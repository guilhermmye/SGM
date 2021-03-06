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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="subdistrito")
public class Subdistrito {
	
	private Integer id;
	private String nome;
	private Distrito distrito;
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subdistrito_id")
	public Integer getId() {
		return id;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "distrito_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Distrito getDistrito()
    {
        return distrito;
    }
	
}
