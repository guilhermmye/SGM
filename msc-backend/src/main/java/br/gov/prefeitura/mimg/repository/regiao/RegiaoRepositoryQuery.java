package br.gov.prefeitura.mimg.repository.regiao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.repository.regiao.filter.RegiaoFilter;

public interface RegiaoRepositoryQuery {
	
	public List<Regiao> filtrar(RegiaoFilter regiaoFilter);
	public Page<Regiao> filtrar(RegiaoFilter regiaoFilter,Pageable pageable);
}
