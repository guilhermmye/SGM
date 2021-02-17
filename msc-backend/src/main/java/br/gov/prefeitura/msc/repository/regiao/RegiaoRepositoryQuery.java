package br.gov.prefeitura.msc.repository.regiao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Regiao;
import br.gov.prefeitura.msc.repository.regiao.filter.RegiaoFilter;

public interface RegiaoRepositoryQuery {
	
	public List<Regiao> filtrar(RegiaoFilter regiaoFilter);
	public Page<Regiao> filtrar(RegiaoFilter regiaoFilter,Pageable pageable);
}
