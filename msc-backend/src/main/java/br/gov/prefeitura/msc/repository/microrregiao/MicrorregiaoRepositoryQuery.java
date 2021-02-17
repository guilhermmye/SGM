package br.gov.prefeitura.msc.repository.microrregiao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Microrregiao;
import br.gov.prefeitura.msc.repository.microrregiao.filter.MicrorregiaoFilter;

public interface MicrorregiaoRepositoryQuery {
	
	public List<Microrregiao> filtrar(MicrorregiaoFilter microrregiaoFilter);
	public Page<Microrregiao> filtrar(MicrorregiaoFilter microrregiaoFilter,Pageable pageable);
}
