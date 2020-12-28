package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.UfRepository;

@RestController
@RequestMapping("/ufs")
public class UfResource {
	
	@Autowired
	private UfRepository  ufRepository;
	
	@GetMapping
	public List<Uf> listar(){
		return ufRepository.findAll();
	}
}
