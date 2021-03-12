package br.gov.prefeitura.stur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.stur.model.Stur;
import br.gov.prefeitura.stur.repository.cidadao.SturRepositoryQuery;

public interface SturRepository extends JpaRepository<Stur, Integer>, SturRepositoryQuery  {

}
