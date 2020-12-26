package sgm.sgm.mimg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="regiao")
public class Regiao {
	
	private Integer id;
	private String nome;
	private String sigla;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "regiao_id")
	public Integer getId() {
		return id;
	}
}
