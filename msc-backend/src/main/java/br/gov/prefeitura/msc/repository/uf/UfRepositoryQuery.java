package br.gov.prefeitura.msc.repository.uf;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Uf;
import br.gov.prefeitura.msc.repository.uf.filter.UfFilter;

public interface UfRepositoryQuery {
	
	public List<Uf> filtrar(UfFilter ufFilter);
	public Page<Uf> filtrar(UfFilter ufFilter,Pageable pageable);
}
