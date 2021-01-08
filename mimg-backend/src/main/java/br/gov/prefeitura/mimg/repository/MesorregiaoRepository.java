package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.repository.mesorregiao.MesorregiaoRepositoryQuery;

public interface MesorregiaoRepository extends JpaRepository<Mesorregiao, Integer> , MesorregiaoRepositoryQuery {

}
