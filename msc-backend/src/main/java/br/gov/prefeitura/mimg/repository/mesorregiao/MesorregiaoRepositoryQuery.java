package br.gov.prefeitura.mimg.repository.mesorregiao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.repository.mesorregiao.filter.MesorregiaoFilter;

public interface MesorregiaoRepositoryQuery {
	
	public List<Mesorregiao> filtrar(MesorregiaoFilter mesorregiaoFilter);
	public Page<Mesorregiao> filtrar(MesorregiaoFilter mesorregiaoFilterr,Pageable pageable);
}
