package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.RegiaoIntermediaria;
import br.gov.prefeitura.msc.repository.RegiaoIntermediariaRepository;

@Service
public class RegiaoIntermediariaService {
	
	@Autowired
	private RegiaoIntermediariaRepository       regiaoIntermediariaRepository;
	
	public RegiaoIntermediaria atualizar(Integer id,RegiaoIntermediaria regiaoIntermediaria) {		
		RegiaoIntermediaria regiaoIntermediariaSalvo = buscarRegiaoIntermediariaPorId(id);		
		BeanUtils.copyProperties(regiaoIntermediaria,regiaoIntermediariaSalvo,"id");		
		return regiaoIntermediariaRepository.save(regiaoIntermediariaSalvo);		
	}

	public RegiaoIntermediaria atualizarNome(Integer id,String nome) {		
		RegiaoIntermediaria regiaoIntermediariaSalvo = buscarRegiaoIntermediariaPorId(id);		
		regiaoIntermediariaSalvo.setNome(nome);
		return regiaoIntermediariaRepository.save(regiaoIntermediariaSalvo);		
	}
	
	private RegiaoIntermediaria buscarRegiaoIntermediariaPorId(Integer id) {
		Optional<RegiaoIntermediaria> regiaoIntermediariaSalvo = regiaoIntermediariaRepository.findById(id);		
		if(regiaoIntermediariaSalvo == null || regiaoIntermediariaSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return regiaoIntermediariaSalvo.get();
	}
	
}
