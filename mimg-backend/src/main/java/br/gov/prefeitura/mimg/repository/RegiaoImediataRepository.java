package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.repository.regiaoImediata.RegiaoImediataRepositoryQuery;

public interface RegiaoImediataRepository extends JpaRepository<RegiaoImediata, Integer> , RegiaoImediataRepositoryQuery  {

}
