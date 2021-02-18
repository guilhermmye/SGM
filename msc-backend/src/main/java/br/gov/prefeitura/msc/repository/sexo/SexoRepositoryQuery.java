package br.gov.prefeitura.msc.repository.sexo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Sexo;
import br.gov.prefeitura.msc.repository.sexo.filter.SexoFilter;

public interface SexoRepositoryQuery {
	
	public List<Sexo> filtrar(SexoFilter sexoFilter);
	public Page<Sexo> filtrar(SexoFilter sexoFilter,Pageable pageable);
}
