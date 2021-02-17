package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Microrregiao;
import br.gov.prefeitura.msc.repository.MicrorregiaoRepository;

@Service
public class MicrorregiaoService {
	
	@Autowired
	private MicrorregiaoRepository       microrregiaoRepository;
	
	public Microrregiao atualizar(Integer id,Microrregiao microrregiao) {		
		Microrregiao microrregiaoSalvo = buscarMicrorregiaoPorId(id);		
		BeanUtils.copyProperties(microrregiao,microrregiaoSalvo,"id");		
		return microrregiaoRepository.save(microrregiaoSalvo);		
	}

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
	
}
