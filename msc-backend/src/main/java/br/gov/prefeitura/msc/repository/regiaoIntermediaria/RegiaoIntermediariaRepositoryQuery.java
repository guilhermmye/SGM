package br.gov.prefeitura.msc.repository.regiaoIntermediaria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.RegiaoIntermediaria;
import br.gov.prefeitura.msc.repository.regiaoIntermediaria.filter.RegiaoIntermediariaFilter;

public interface RegiaoIntermediariaRepositoryQuery {
	
	public List<RegiaoIntermediaria> filtrar(RegiaoIntermediariaFilter regiaoIntermediariaFilter);
	public Page<RegiaoIntermediaria> filtrar(RegiaoIntermediariaFilter regiaoIntermediariaFilter,Pageable pageable);
}
