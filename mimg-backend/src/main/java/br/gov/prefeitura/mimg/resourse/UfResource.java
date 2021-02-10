package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.UfRepository;
import br.gov.prefeitura.mimg.repository.uf.filter.UfFilter;
import br.gov.prefeitura.mimg.service.UfService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ufs")
public class UfResource {
	
	@Autowired
	private UfRepository                ufRepository;
	
	@Autowired
	private ApplicationEventPublisher   publisher;
	
	@Autowired
	private UfService                   ufService;
	
	
	@GetMapping
	public Page<Uf> pesquisar(UfFilter ufFilter,Pageable pageable){
		return ufRepository.filtrar(ufFilter,pageable);
	}
	
//	@GetMapping
//	public List<Uf> listar(){
//		return ufRepository.findAll();
//	}
}
