package br.gov.prefeitura.mimg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.Municipio;
import br.gov.prefeitura.mimg.repository.MunicipioRepository;

@Service
public class MunicipioService {
	
	@Autowired
	private MunicipioRepository       municipioRepository;
	
	@Transactional
	public Municipio atualizar(Integer id,Municipio municipio) {		
		Municipio municipioSalvo = buscarMunicipioPorId(id);		
		BeanUtils.copyProperties(municipio,municipioSalvo,"id");		
		return municipioRepository.save(municipioSalvo);		
	}

	@Transactional
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
	
	@Transactional
	public void salvarMunicipio(List<Municipio> municipios) {		
			
           for (Municipio municipio2 : municipios) {
			
			if(municipio2.getId() == null)
			{
				municipio2.setId(-1);
			}	
			municipioRepository.save(municipio2);
		}
		
	}
   
   
	
}
