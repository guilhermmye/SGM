package br.gov.prefeitura.mimg.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.UfRepository;

@Service
public class UfService {
	
	@Autowired
	private UfRepository       ufRepository;
	
	public Uf atualizar(Integer id,Uf uf) {		
		Uf ufSalvo = buscarUfPorId(id);		
		BeanUtils.copyProperties(uf,ufSalvo,"id");		
		return ufRepository.save(ufSalvo);		
	}

	public Uf atualizarNome(Integer id,String nome) {		
		Uf ufSalvo = buscarUfPorId(id);		
		ufSalvo.setNome(nome);
		return ufRepository.save(ufSalvo);		
	}
	
	private Uf buscarUfPorId(Integer id) {
		Optional<Uf> ufSalvo = ufRepository.findById(id);		
		if(ufSalvo == null || ufSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return ufSalvo.get();
	}
	
}
