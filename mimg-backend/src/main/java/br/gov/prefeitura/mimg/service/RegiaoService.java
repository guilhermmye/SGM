package br.gov.prefeitura.mimg.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
	
	public Regiao buscarRegiaoPorId(Integer id) {
		Optional<Regiao> regiaoSalvo = regiaoRepository.findById(id);		
		if(regiaoSalvo == null || regiaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return regiaoSalvo.get();
	}
	
	@Transactional
	public void salvarRegioes(List<Regiao> regioes) {		
			
           for (Regiao regiao2 : regioes) {
			
			if(regiao2.getId() == null)
			{
				regiao2.setId(-1);
			}	
			regiaoRepository.save(regiao2);
		}
		
	}
   
   
   
	
}
