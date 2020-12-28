package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.repository.MesorregiaoRepository;

@RestController
@RequestMapping("/mesorregioes")
public class MesorregiaoResource {
	
	@Autowired
	private MesorregiaoRepository  mesorregiaoRepository;
	
	@GetMapping
	public List<Mesorregiao> listar(){
		return mesorregiaoRepository.findAll();
	}
}
