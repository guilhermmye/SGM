package br.gov.prefeitura.mimg.resourse;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.MicrorregiaoRepository;
import br.gov.prefeitura.mimg.repository.mesorregiao.filter.MesorregiaoFilter;
import br.gov.prefeitura.mimg.repository.microrregiao.filter.MicrorregiaoFilter;
import br.gov.prefeitura.mimg.service.MicrorregiaoService;
import br.gov.prefeitura.mimg.service.UfService;

@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {
	
	@Autowired
	private MicrorregiaoRepository  microrregiaoRepository;
	
	@Autowired
	private MicrorregiaoService           microrregiaoService;
	
	@GetMapping
	public List<Microrregiao> listar(){
		return microrregiaoRepository.findAll();
	}
	

	@GetMapping
	public Page<Microrregiao> pesquisar(MicrorregiaoFilter microrregiaoFilter,Pageable pageable){
		return microrregiaoRepository.filtrar(microrregiaoFilter,pageable);
	}
	
	
	@RequestMapping( value ="/ibge/{id}", method = RequestMethod.GET)
	public ResponseEntity<Microrregiao[]> pesquisarIbge(@PathVariable String id){
					
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("mesorregioes")
				.queryParam(id)
				.build();
		
		ResponseEntity<Microrregiao[]> microrregiao = restTemplate.getForEntity(uri.toUriString(), Microrregiao[].class);
		
		List<Microrregiao> microrregioes = Arrays.asList(microrregiao.getBody());
		
		microrregiaoService.salvarMicrorregioes(microrregioes);
		return microrregiao;		
	} 
	
	
	
}
