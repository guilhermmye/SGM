package br.gov.prefeitura.msc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.prefeitura.msc.model.TipoPessoa;
import br.gov.prefeitura.msc.repository.tipoPessoa.TipoPessoaRepositoryQuery;

public interface TipoPessoaRepository extends JpaRepository<TipoPessoa, Integer>, TipoPessoaRepositoryQuery  {

}
