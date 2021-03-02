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
@Table(name="microrregiao")
public class Microrregiao {
	
	private Integer id;
	private String nome;
	private Mesorregiao mesorregiao;
	
	
	@Id
	@Column(name = "microrregiao_id")
	public Integer getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mesorregiao_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    public Mesorregiao getMesorregiao()
    {
        return mesorregiao;
    }

}
