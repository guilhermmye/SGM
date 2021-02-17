package br.gov.prefeitura.msc.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.msc.model.Subdistrito;
import br.gov.prefeitura.msc.repository.SubdistritoRepository;
import br.gov.prefeitura.msc.repository.subdistrito.filter.SubdistritoFilter;
import br.gov.prefeitura.msc.service.SubdistritoService;

@RestController
@RequestMapping("/subdistritos")
public class SubdistritoResource {
	
	@Autowired
	private SubdistritoRepository       subdistritoRepository;
	
	@Autowired
	private ApplicationEventPublisher   publisher;
	
	@Autowired
	private SubdistritoService          subdistritoService;
	
	
	@GetMapping
	public Page<Subdistrito> pesquisar(SubdistritoFilter subdistritoFilter,Pageable pageable){
		return subdistritoRepository.filtrar(subdistritoFilter,pageable);
	}
	
//	@GetMapping
//	public List<Subdistrito> listar(){
//		return subdistritoRepository.findAll();
//	}
}
