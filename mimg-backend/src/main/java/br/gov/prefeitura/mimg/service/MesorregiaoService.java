package br.gov.prefeitura.mimg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.repository.MesorregiaoRepository;

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
	
	@Transactional
	public void salvarMesorregioes(List<Mesorregiao> Mesorregioes) {		
			
           for (Mesorregiao mesorregioes2 : Mesorregioes) {
			
			if(mesorregioes2.getId() == null)
			{
				mesorregioes2.setId(-1);
			}	
			mesorregiaoRepository.save(mesorregioes2);
		}
		
	}
   
	
}
