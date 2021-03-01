package br.gov.prefeitura.mimg.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.uf.UfRepositoryQuery;


public interface UfRepository extends JpaRepository<Uf, Integer>, UfRepositoryQuery   {
	

}
