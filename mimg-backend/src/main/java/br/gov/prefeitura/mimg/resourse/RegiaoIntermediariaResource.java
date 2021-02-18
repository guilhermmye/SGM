package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.repository.RegiaoIntermediariaRepository;

@RestController
@RequestMapping("/regioesIntermediarias")
public class RegiaoIntermediariaResource {
	
	@Autowired
	private RegiaoIntermediariaRepository  regiaoIntermediariaRepository;
	
	@GetMapping
	public List<RegiaoIntermediaria> listar(){
		return regiaoIntermediariaRepository.findAll();
	}
}
