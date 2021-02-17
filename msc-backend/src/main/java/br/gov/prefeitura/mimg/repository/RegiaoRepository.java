package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.repository.regiao.RegiaoRepositoryQuery;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer>, RegiaoRepositoryQuery  {

}
