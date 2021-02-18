package br.gov.prefeitura.msc.repository.pessoa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Pessoa;
import br.gov.prefeitura.msc.repository.pessoa.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	public List<Pessoa> filtrar(PessoaFilter regiaoFilter);
	public Page<Pessoa> filtrar(PessoaFilter regiaoFilter,Pageable pageable);
}
