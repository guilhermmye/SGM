package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.repository.RegiaoRepository;

@RestController
@RequestMapping("/regioes")
public class RegiaoResource {
	
	@Autowired
	private RegiaoRepository  regiaoRepository;
	
	@GetMapping
	public List<Regiao> listar(){
		return regiaoRepository.findAll();
	}
}
