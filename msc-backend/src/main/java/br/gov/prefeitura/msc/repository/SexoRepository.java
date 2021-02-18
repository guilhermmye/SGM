package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Sexo;
import br.gov.prefeitura.msc.repository.sexo.SexoRepositoryQuery;

public interface SexoRepository extends JpaRepository<Sexo, Integer>, SexoRepositoryQuery  {

}
