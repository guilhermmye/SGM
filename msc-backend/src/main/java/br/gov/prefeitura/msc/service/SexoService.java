package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Sexo;
import br.gov.prefeitura.msc.repository.SexoRepository;

@Service
public class SexoService {
	
	@Autowired
	private SexoRepository       sexoRepository;
	
	public Sexo atualizar(Integer id,Sexo sexo) {		
		Sexo sexoSalvo = buscarSexoPorId(id);		
		BeanUtils.copyProperties(sexo,sexoSalvo,"id");		
		return sexoRepository.save(sexoSalvo);		
	}
	
	private Sexo buscarSexoPorId(Integer id) {
		Optional<Sexo> sexoSalvo = sexoRepository.findById(id);		
		if(sexoSalvo == null || sexoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return sexoSalvo.get();
	}	
}
