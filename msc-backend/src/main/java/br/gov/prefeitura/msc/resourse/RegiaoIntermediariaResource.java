package br.gov.prefeitura.msc.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.msc.model.RegiaoIntermediaria;
import br.gov.prefeitura.msc.repository.RegiaoIntermediariaRepository;
import br.gov.prefeitura.msc.repository.regiaoIntermediaria.filter.RegiaoIntermediariaFilter;
import br.gov.prefeitura.msc.service.RegiaoIntermediariaService;

@RestController
@RequestMapping("/regioesIntermediarias")
public class RegiaoIntermediariaResource {
	
	@Autowired
	private RegiaoIntermediariaRepository  regiaoIntermediariaRepository;
	
	@Autowired
	private ApplicationEventPublisher      publisher;
	
	@Autowired
	private RegiaoIntermediariaService     regiaoIntermediariaService;

	
	@GetMapping
	public Page<RegiaoIntermediaria> pesquisar(RegiaoIntermediariaFilter regiaoIntermediariaFilter,Pageable pageable){
		return regiaoIntermediariaRepository.filtrar(regiaoIntermediariaFilter,pageable);
	}
		
//	@GetMapping
//	public List<RegiaoIntermediaria> listar(){
//		return regiaoIntermediariaRepository.findAll();
//	}
}
