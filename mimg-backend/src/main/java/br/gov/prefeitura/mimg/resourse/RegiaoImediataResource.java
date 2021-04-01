package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.repository.RegiaoImediataRepository;
import br.gov.prefeitura.mimg.repository.regiaoImediata.filter.RegiaoImediataFilter;

@RestController
@RequestMapping("/regioesImediatas")
public class RegiaoImediataResource {
	
	@Autowired
	private RegiaoImediataRepository  	regiaoImediataRepository;
	
	@GetMapping
	public Page<RegiaoImediata> pesquisar(RegiaoImediataFilter regiaoImediataFilter,Pageable pageable){
		return regiaoImediataRepository.filtrar(regiaoImediataFilter, pageable);
	}
}
