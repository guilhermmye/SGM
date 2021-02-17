package br.gov.prefeitura.msc.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.msc.model.Microrregiao;
import br.gov.prefeitura.msc.repository.MicrorregiaoRepository;

@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {
	
	@Autowired
	private MicrorregiaoRepository  microrregiaoRepository;
	
	@GetMapping
	public List<Microrregiao> listar(){
		return microrregiaoRepository.findAll();
	}
}
