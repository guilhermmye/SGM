package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.Microrregiao;
import br.gov.prefeitura.msc.repository.microrregiao.MicrorregiaoRepositoryQuery;

public interface MicrorregiaoRepository extends JpaRepository<Microrregiao, Integer> , MicrorregiaoRepositoryQuery {

}
