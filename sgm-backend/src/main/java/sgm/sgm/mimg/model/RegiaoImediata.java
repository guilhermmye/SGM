package sgm.sgm.mimg.model;

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
@Table(name="regiaoIntermedia")
public class RegiaoImediata {
	
	private Integer id;
	private String nome;
	private RegiaoIntermediaria regiaoIntermediaria;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "regiaoImediata_id")
	public Integer getId() {
		return id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regiaoIntermediaria_id")
    public RegiaoIntermediaria getRegiaoIntermediaria()
    {
        return regiaoIntermediaria;
    }
}
