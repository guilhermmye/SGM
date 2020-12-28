package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Subdistrito;
import br.gov.prefeitura.mimg.repository.SubdistritoRepository;

@RestController
@RequestMapping("/subdistritos")
public class SubdistritoResource {
	
	@Autowired
	private SubdistritoRepository  subdistritoRepository;
	
	@GetMapping
	public List<Subdistrito> listar(){
		return subdistritoRepository.findAll();
	}
}
