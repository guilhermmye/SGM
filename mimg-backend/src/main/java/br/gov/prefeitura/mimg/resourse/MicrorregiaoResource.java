package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.repository.MicrorregiaoRepository;
import br.gov.prefeitura.mimg.repository.microrregiao.filter.MicrorregiaoFilter;

@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {
	
	@Autowired
	private MicrorregiaoRepository  		microrregiaoRepository;
	
	@GetMapping
	public Page<Microrregiao> pesquisar(MicrorregiaoFilter microrregiaoFilter,Pageable pageable){
		return microrregiaoRepository.filtrar(microrregiaoFilter,pageable);
	}
	
}
