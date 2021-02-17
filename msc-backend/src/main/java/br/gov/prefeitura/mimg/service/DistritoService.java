package br.gov.prefeitura.mimg.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.Distrito;
import br.gov.prefeitura.mimg.repository.DistritoRepository;

@Service
public class DistritoService {
	
	@Autowired
	private DistritoRepository       distritoRepository;
	
	public Distrito atualizar(Integer id,Distrito distrito) {		
		Distrito distritoSalvo = buscarDistritoPorId(id);		
		BeanUtils.copyProperties(distrito,distritoSalvo,"id");		
		return distritoRepository.save(distritoSalvo);		
	}

	public Distrito atualizarNome(Integer id,String nome) {		
		Distrito distritoSalvo = buscarDistritoPorId(id);		
		distritoSalvo.setNome(nome);
		return distritoRepository.save(distritoSalvo);		
	}
	
	private Distrito buscarDistritoPorId(Integer id) {
		Optional<Distrito> distritoSalvo = distritoRepository.findById(id);		
		if(distritoSalvo == null || distritoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return distritoSalvo.get();
	}
	
}
