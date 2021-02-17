package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Uf;
import br.gov.prefeitura.msc.repository.uf.UfRepositoryQuery;

public interface UfRepository extends JpaRepository<Uf, Integer>, UfRepositoryQuery   {

}
