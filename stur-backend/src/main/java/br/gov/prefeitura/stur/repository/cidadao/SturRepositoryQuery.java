package br.gov.prefeitura.stur.repository.cidadao;

import java.util.List;

import br.gov.prefeitura.stur.model.Stur;

public interface SturRepositoryQuery {
	
	public List<Stur> filtrarPorCpfCnpj(String cpfCnpj);
}
