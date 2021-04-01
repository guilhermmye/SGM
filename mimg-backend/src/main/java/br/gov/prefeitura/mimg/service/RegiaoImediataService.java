package br.gov.prefeitura.mimg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.repository.RegiaoImediataRepository;

@Service
public class RegiaoImediataService {
	
	@Autowired
	private RegiaoImediataRepository       regiaoImediataRepository;

	@Transactional
	public RegiaoImediata atualizar(Integer id,RegiaoImediata regiaoImediata) {		
		RegiaoImediata regiaoImediataSalvo = buscarRegiaoImediataPorId(id);		
		BeanUtils.copyProperties(regiaoImediata,regiaoImediataSalvo,"id");		
		return regiaoImediataRepository.save(regiaoImediataSalvo);		
	}

	@Transactional
	public RegiaoImediata atualizarNome(Integer id,String nome) {		
		RegiaoImediata regiaoImediataSalvo = buscarRegiaoImediataPorId(id);		
		regiaoImediataSalvo.setNome(nome);
		return regiaoImediataRepository.save(regiaoImediataSalvo);		
	}
	
	private RegiaoImediata buscarRegiaoImediataPorId(Integer id) {
		Optional<RegiaoImediata> regiaoImediataSalvo = regiaoImediataRepository.findById(id);		
		if(regiaoImediataSalvo == null || regiaoImediataSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return regiaoImediataSalvo.get();
	}
	
	@Transactional
	public void salvarRegiaoImediatas(List<RegiaoImediata> regiaoImediatas) {		
			
           for (RegiaoImediata regiaoImediata2 : regiaoImediatas) {
			
			if(regiaoImediata2.getId() == null)
			{
				regiaoImediata2.setId(-1);
			}
			regiaoImediataRepository.save(regiaoImediata2);
		}			
	}
	
	
}
