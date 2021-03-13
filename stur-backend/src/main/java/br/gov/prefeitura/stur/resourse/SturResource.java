package br.gov.prefeitura.stur.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.stur.model.Stur;
import br.gov.prefeitura.stur.repository.SturRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/stur")
public class SturResource {
	
	@Autowired
	private SturRepository  			cidadaoRepository;
	

	@GetMapping("/{cpfCnpj}")
	public List<Stur> pesquisar(@PathVariable String cpfCnpj){
		return cidadaoRepository.filtrarPorCpfCnpj(cpfCnpj);
	}
	
}
