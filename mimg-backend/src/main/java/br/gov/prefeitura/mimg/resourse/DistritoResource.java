package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Distrito;
import br.gov.prefeitura.mimg.repository.DistritoRepository;

@RestController
@RequestMapping("/distritos")
public class DistritoResource {
	
	@Autowired
	private DistritoRepository  distritoRepository;
	
	@GetMapping
	public List<Distrito> listar(){
		return distritoRepository.findAll();
	}
}
