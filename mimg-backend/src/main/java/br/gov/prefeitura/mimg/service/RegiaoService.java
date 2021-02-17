package br.gov.prefeitura.mimg.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
