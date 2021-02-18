package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.TipoPessoa;
import br.gov.prefeitura.msc.repository.TipoPessoaRepository;

@Service
public class TipoPessoaService {
	
	@Autowired
	private TipoPessoaRepository       tipoPessoaRepository;
	
	public TipoPessoa atualizar(Integer id,TipoPessoa tipoPessoa) {		
		TipoPessoa tipoPessoaSalvo = buscarTipoPessoaPorId(id);		
		BeanUtils.copyProperties(tipoPessoa,tipoPessoaSalvo,"id");		
		return tipoPessoaRepository.save(tipoPessoaSalvo);		
	}
	
	private TipoPessoa buscarTipoPessoaPorId(Integer id) {
		Optional<TipoPessoa> tipoPessoaSalvo = tipoPessoaRepository.findById(id);		
		if(tipoPessoaSalvo == null || tipoPessoaSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return tipoPessoaSalvo.get();
	}
	
}
