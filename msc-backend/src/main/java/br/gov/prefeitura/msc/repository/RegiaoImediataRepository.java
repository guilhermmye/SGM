package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.RegiaoImediata;
import br.gov.prefeitura.msc.repository.regiaoImediata.RegiaoImediataRepositoryQuery;

public interface RegiaoImediataRepository extends JpaRepository<RegiaoImediata, Integer> , RegiaoImediataRepositoryQuery  {

}
