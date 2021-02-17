package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Regiao;
import br.gov.prefeitura.msc.repository.regiao.RegiaoRepositoryQuery;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer>, RegiaoRepositoryQuery  {

}
