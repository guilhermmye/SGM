package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.Distrito;
import br.gov.prefeitura.mimg.repository.distrito.DistritoRepositoryQuery;

public interface DistritoRepository extends JpaRepository<Distrito, Integer>, DistritoRepositoryQuery {

}
