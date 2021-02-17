package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Mesorregiao;
import br.gov.prefeitura.msc.repository.MesorregiaoRepository;

@Service
public class MesorregiaoService {
	
	@Autowired
	private MesorregiaoRepository       mesorregiaoRepository;
	
	public Mesorregiao atualizar(Integer id,Mesorregiao mesorregiao) {		
		Mesorregiao mesorregiaoSalvo = buscarMesorregiaoPorId(id);		
		BeanUtils.copyProperties(mesorregiao,mesorregiaoSalvo,"id");		
		return mesorregiaoRepository.save(mesorregiaoSalvo);		
	}

	public Mesorregiao atualizarNome(Integer id,String nome) {		
		Mesorregiao mesorregiaoSalvo = buscarMesorregiaoPorId(id);		
		mesorregiaoSalvo.setNome(nome);
		return mesorregiaoRepository.save(mesorregiaoSalvo);		
	}
	
	private Mesorregiao buscarMesorregiaoPorId(Integer id) {
		Optional<Mesorregiao> mesorregiaoSalvo = mesorregiaoRepository.findById(id);		
		if(mesorregiaoSalvo == null || mesorregiaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return mesorregiaoSalvo.get();
	}
	
}
