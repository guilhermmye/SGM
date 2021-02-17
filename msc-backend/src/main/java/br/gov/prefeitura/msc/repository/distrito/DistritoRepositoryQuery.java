package br.gov.prefeitura.msc.repository.distrito;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Distrito;
import br.gov.prefeitura.msc.repository.distrito.filter.DistritoFilter;

public interface DistritoRepositoryQuery {
	
	public List<Distrito> filtrar(DistritoFilter distritoFilter);
	public Page<Distrito> filtrar(DistritoFilter distritoFilter,Pageable pageable);
}
