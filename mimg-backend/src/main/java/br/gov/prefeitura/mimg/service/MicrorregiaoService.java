package br.gov.prefeitura.mimg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.repository.MicrorregiaoRepository;

@Service
public class MicrorregiaoService {
	
	@Autowired
	private MicrorregiaoRepository       microrregiaoRepository;
	
	@Transactional
	public Microrregiao atualizar(Integer id,Microrregiao microrregiao) {		
		Microrregiao microrregiaoSalvo = buscarMicrorregiaoPorId(id);		
		BeanUtils.copyProperties(microrregiao,microrregiaoSalvo,"id");		
		return microrregiaoRepository.save(microrregiaoSalvo);		
	}

	@Transactional
	public Microrregiao atualizarNome(Integer id,String nome) {		
		Microrregiao microrregiaoSalvo = buscarMicrorregiaoPorId(id);		
		microrregiaoSalvo.setNome(nome);
		return microrregiaoRepository.save(microrregiaoSalvo);		
	}
	
	private Microrregiao buscarMicrorregiaoPorId(Integer id) {
		Optional<Microrregiao> microrregiaoSalvo = microrregiaoRepository.findById(id);		
		if(microrregiaoSalvo == null || microrregiaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return microrregiaoSalvo.get();
	}
	
	@Transactional
	public void salvarMicrorregioes(List<Microrregiao> microrregioes) {		
			
           for (Microrregiao microrregioes2 : microrregioes) {
			
			if(microrregioes2.getId() == null)
			{
				microrregioes2.setId(-1);
			}	
			microrregiaoRepository.save(microrregioes2);
		}
		
	}
}
