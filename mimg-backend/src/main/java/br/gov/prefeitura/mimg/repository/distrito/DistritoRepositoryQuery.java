package br.gov.prefeitura.mimg.repository.distrito;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.mimg.model.Distrito;
import br.gov.prefeitura.mimg.repository.filter.DistritoFilter;

public interface DistritoRepositoryQuery {
	
	public List<Distrito> filtrar(DistritoFilter distritoFilter);
	public Page<Distrito> filtrar(DistritoFilter distritoFilter,Pageable pageable);
}
