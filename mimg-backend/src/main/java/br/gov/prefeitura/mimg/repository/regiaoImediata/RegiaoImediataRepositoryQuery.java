package br.gov.prefeitura.mimg.repository.regiaoImediata;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.repository.regiaoImediata.filter.RegiaoImediataFilter;

public interface RegiaoImediataRepositoryQuery {
	
	public List<RegiaoImediata> filtrar(RegiaoImediataFilter regiaoImediataFilter);
	public Page<RegiaoImediata> filtrar(RegiaoImediataFilter regiaoImediataFilter,Pageable pageable);
}
