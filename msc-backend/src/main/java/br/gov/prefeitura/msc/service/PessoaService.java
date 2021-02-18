package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Pessoa;
import br.gov.prefeitura.msc.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository      pessoaRepository;
	
	public Pessoa atualizar(Integer id,Pessoa pessoa) {		
		Pessoa pessoaSalvo = buscarPessoaPorId(id);		
		BeanUtils.copyProperties(pessoa,pessoaSalvo,"id");		
		return pessoaRepository.save(pessoaSalvo);		
	}
	
	private Pessoa buscarPessoaPorId(Integer id) {
		Optional<Pessoa> pessoaSalvo = pessoaRepository.findById(id);		
		if(pessoaSalvo == null || pessoaSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalvo.get();
	}

}
