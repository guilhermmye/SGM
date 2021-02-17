package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Municipio;
import br.gov.prefeitura.msc.repository.MunicipioRepository;

@Service
public class MunicipioService {
	
	@Autowired
	private MunicipioRepository       municipioRepository;
	
	public Municipio atualizar(Integer id,Municipio municipio) {		
		Municipio municipioSalvo = buscarMunicipioPorId(id);		
		BeanUtils.copyProperties(municipio,municipioSalvo,"id");		
		return municipioRepository.save(municipioSalvo);		
	}

	public Municipio atualizarNome(Integer id,String nome) {		
		Municipio municipioSalvo = buscarMunicipioPorId(id);		
		municipioSalvo.setNome(nome);
		return municipioRepository.save(municipioSalvo);		
	}
	
	private Municipio buscarMunicipioPorId(Integer id) {
		Optional<Municipio> municipioSalvo = municipioRepository.findById(id);		
		if(municipioSalvo == null || municipioSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return municipioSalvo.get();
	}
	
}
