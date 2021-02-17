package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Mesorregiao;
import br.gov.prefeitura.msc.repository.mesorregiao.MesorregiaoRepositoryQuery;

public interface MesorregiaoRepository extends JpaRepository<Mesorregiao, Integer> , MesorregiaoRepositoryQuery {

}
