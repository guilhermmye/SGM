package br.gov.prefeitura.msc.model;

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
@Table(name="subdistrito_id")
public class Subdistrito {
	
	private Integer id;
	private String nome;
	private Distrito distrito;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subdistrito_id")
	public Integer getId() {
		return id;
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "distrito_id")
    public Distrito getDistrito()
    {
        return distrito;
    }
	
}
