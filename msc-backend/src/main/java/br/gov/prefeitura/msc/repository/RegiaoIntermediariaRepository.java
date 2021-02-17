package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.RegiaoIntermediaria;
import br.gov.prefeitura.msc.repository.regiaoIntermediaria.RegiaoIntermediariaRepositoryQuery;

public interface RegiaoIntermediariaRepository extends JpaRepository<RegiaoIntermediaria, Integer> ,RegiaoIntermediariaRepositoryQuery   {

}
