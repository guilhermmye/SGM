package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Distrito;
import br.gov.prefeitura.msc.repository.distrito.DistritoRepositoryQuery;

public interface DistritoRepository extends JpaRepository<Distrito, Integer>, DistritoRepositoryQuery {

}
