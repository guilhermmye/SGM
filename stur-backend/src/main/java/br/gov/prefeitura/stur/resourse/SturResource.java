package br.gov.prefeitura.stur.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public Page<Stur> pesquisar(String cpfCnpj,Pageable pageable){
		return cidadaoRepository.filtrarPorCpfCnpj(cpfCnpj,pageable);
	}
	
}
