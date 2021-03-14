package br.gov.prefeitura.mimg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.RegiaoIntermediariaRepository;

@Service
public class RegiaoIntermediariaService {
	
	@Autowired
	private RegiaoIntermediariaRepository       regiaoIntermediariaRepository;
	@Autowired
	private UfService      ufService;
	
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
	@Transactional
	public void salvarRegiaoIntermediarias(List<RegiaoIntermediaria> regiaoIntermediarias) {		
			
           for (RegiaoIntermediaria regiaoIntermediaria2 : regiaoIntermediarias) {
			
			if(regiaoIntermediaria2.getId() == null)
			{
				regiaoIntermediaria2.setId(-1);
			}
			
			Uf uf = ufService.buscarUfPorId(regiaoIntermediaria2.getUf().getId());
			regiaoIntermediaria2.setUf(uf);
			regiaoIntermediariaRepository.save(regiaoIntermediaria2);
			regiaoIntermediariaRepository.flush();
			
		}
		
			
	}
	
}
