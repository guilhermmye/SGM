package br.gov.prefeitura.msc.repository.municipio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Municipio;
import br.gov.prefeitura.msc.repository.municipio.filter.MunicipioFilter;

public interface MunicipioRepositoryQuery {
	
	public List<Municipio> filtrar(MunicipioFilter municipioFilter);
	public Page<Municipio> filtrar(MunicipioFilter municipioFilter,Pageable pageable);
}
