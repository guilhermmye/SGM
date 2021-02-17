package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Subdistrito;
import br.gov.prefeitura.msc.repository.SubdistritoRepository;

@Service
public class SubdistritoService {
	
	@Autowired
	private SubdistritoRepository       subdistritoRepository;
	
	public Subdistrito atualizar(Integer id,Subdistrito distrito) {		
		Subdistrito subdistritoSalvo = buscarSubdistritoPorId(id);		
		BeanUtils.copyProperties(distrito,subdistritoSalvo,"id");		
		return subdistritoRepository.save(subdistritoSalvo);		
	}

	public Subdistrito atualizarNome(Integer id,String nome) {		
		Subdistrito subdistritoSalvo = buscarSubdistritoPorId(id);		
		subdistritoSalvo.setNome(nome);
		return subdistritoRepository.save(subdistritoSalvo);		
	}
	
	private Subdistrito buscarSubdistritoPorId(Integer id) {
		Optional<Subdistrito> subdistritoSalvo = subdistritoRepository.findById(id);		
		if(subdistritoSalvo == null || subdistritoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return subdistritoSalvo.get();
	}
	
}
