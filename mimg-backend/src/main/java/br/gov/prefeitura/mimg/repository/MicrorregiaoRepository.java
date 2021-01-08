package br.gov.prefeitura.mimg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.repository.microrregiao.MicrorregiaoRepositoryQuery;

public interface MicrorregiaoRepository extends JpaRepository<Microrregiao, Integer> , MicrorregiaoRepositoryQuery {

}
