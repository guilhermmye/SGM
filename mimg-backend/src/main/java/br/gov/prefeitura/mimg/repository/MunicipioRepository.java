package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer>  {

}
