package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Cidadao;
import br.gov.prefeitura.msc.repository.cidadao.CidadaoRepositoryQuery;

public interface CidadaoRepository extends JpaRepository<Cidadao, Integer>, CidadaoRepositoryQuery  {

}
