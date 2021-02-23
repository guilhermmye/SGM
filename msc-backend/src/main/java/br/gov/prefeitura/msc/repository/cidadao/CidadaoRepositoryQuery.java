package br.gov.prefeitura.msc.repository.cidadao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Cidadao;
import br.gov.prefeitura.msc.repository.cidadao.filter.CidadaoFilter;

public interface CidadaoRepositoryQuery {
	
	public List<Cidadao> filtrar(CidadaoFilter cidadaoFilter);
	public Page<Cidadao> filtrar(CidadaoFilter cidadaoFilter,Pageable pageable);
}
