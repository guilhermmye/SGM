package br.gov.prefeitura.msc.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.msc.model.Municipio;
import br.gov.prefeitura.msc.repository.MunicipioRepository;

@RestController
@RequestMapping("/municipios")
public class MunicipioResource {
	
	@Autowired
	private MunicipioRepository  municipioRepository;
	
	@GetMapping
	public List<Municipio> listar(){
		return municipioRepository.findAll();
	}
}
