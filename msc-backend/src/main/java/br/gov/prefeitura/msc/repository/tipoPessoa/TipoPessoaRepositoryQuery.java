package br.gov.prefeitura.msc.repository.tipoPessoa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.TipoPessoa;
import br.gov.prefeitura.msc.repository.tipoPessoa.filter.TipoPessoaFilter;

public interface TipoPessoaRepositoryQuery {
	
	public List<TipoPessoa> filtrar(TipoPessoaFilter tipoPessoaFilter);
	public Page<TipoPessoa> filtrar(TipoPessoaFilter tipoPessoaFilter,Pageable pageable);
}
