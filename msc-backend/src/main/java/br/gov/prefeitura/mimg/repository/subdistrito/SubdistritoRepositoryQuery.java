package br.gov.prefeitura.mimg.repository.subdistrito;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.mimg.model.Subdistrito;
import br.gov.prefeitura.mimg.repository.subdistrito.filter.SubdistritoFilter;

public interface SubdistritoRepositoryQuery {
	
	public List<Subdistrito> filtrar(SubdistritoFilter subdistritoFilter);
	public Page<Subdistrito> filtrar(SubdistritoFilter subdistritoFilter,Pageable pageable);
}
