package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.repository.RegiaoImediataRepository;

@RestController
@RequestMapping("/regioesImediatas")
public class RegiaoImediataResource {
	
	@Autowired
	private RegiaoImediataRepository  regiaoImediataRepository;
	
	@GetMapping
	public List<RegiaoImediata> listar(){
		return regiaoImediataRepository.findAll();
	}
}
