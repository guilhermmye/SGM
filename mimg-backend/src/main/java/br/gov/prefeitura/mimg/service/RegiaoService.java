package br.gov.prefeitura.mimg.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.repository.RegiaoRepository;

@Service
public class RegiaoService {
	
	@Autowired
	private RegiaoRepository       regiaoRepository;
	
	public Regiao atualizar(Integer id,Regiao regiao) {		
		Regiao regiaoSalvo = buscarRegiaoPorId(id);		
		BeanUtils.copyProperties(regiao,regiaoSalvo,"id");		
		return regiaoRepository.save(regiaoSalvo);		
	}
	
	private Regiao buscarRegiaoPorId(Integer id) {
		Optional<Regiao> regiaoSalvo = regiaoRepository.findById(id);		
		if(regiaoSalvo == null || regiaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return regiaoSalvo.get();
	}
	
}
