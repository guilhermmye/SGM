package br.gov.prefeitura.stur.repository.cidadao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.stur.model.Stur;

public interface SturRepositoryQuery {
	
	public Page<Stur> filtrarPorCpfCnpj(String cpfCnpj,Pageable pageable);
}
