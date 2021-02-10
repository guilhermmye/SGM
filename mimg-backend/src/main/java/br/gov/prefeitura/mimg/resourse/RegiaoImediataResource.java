package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.repository.RegiaoImediataRepository;
import br.gov.prefeitura.mimg.repository.regiaoImediata.filter.RegiaoImediataFilter;
import br.gov.prefeitura.mimg.service.SubdistritoService;

@RestController
@RequestMapping("/regioesImediatas")
public class RegiaoImediataResource {
	
	@Autowired
	private RegiaoImediataRepository    regiaoImediataRepository;
		
	@Autowired
	private ApplicationEventPublisher   publisher;
	
	@Autowired
	private SubdistritoService          subdistritoService;
	
	
	
	@GetMapping
	public Page<RegiaoImediata> pesquisar(RegiaoImediataFilter regiaoImediataFilter,Pageable pageable){
		return regiaoImediataRepository.filtrar(regiaoImediataFilter,pageable);
	}
	
//	@GetMapping
//	public List<RegiaoImediata> listar(){
//		return regiaoImediataRepository.findAll();
//	}
}
