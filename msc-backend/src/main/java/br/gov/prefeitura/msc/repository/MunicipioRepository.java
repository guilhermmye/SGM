package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Municipio;
import br.gov.prefeitura.msc.repository.municipio.MunicipioRepositoryQuery;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer>  , MunicipioRepositoryQuery {

}
