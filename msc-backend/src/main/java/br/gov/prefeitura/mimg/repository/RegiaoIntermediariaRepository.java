package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.repository.regiaoIntermediaria.RegiaoIntermediariaRepositoryQuery;

public interface RegiaoIntermediariaRepository extends JpaRepository<RegiaoIntermediaria, Integer> ,RegiaoIntermediariaRepositoryQuery   {

}
