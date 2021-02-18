package br.gov.prefeitura.mimg.resourse;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.UfRepository;
import br.gov.prefeitura.mimg.service.UfService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ufs")
public class UfResource {
	
	@Autowired
	private UfRepository  ufRepository;
	
	@Autowired
	private UfService  ufService;
	
	@GetMapping
	public List<Uf> listar(){
		return ufRepository.findAll();
	}
	
	@RequestMapping( value ="/ibge/{id}", method = RequestMethod.GET)
	public ResponseEntity<Uf[]> pesquisarIbge(@PathVariable String id){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("estados")
				.queryParam(id)
				.build();
		
		ResponseEntity<Uf[]> uf = restTemplate.getForEntity(uri.toUriString(), Uf[].class);
		
		List<Uf> ufs = Arrays.asList(uf.getBody());
	
		ufService.salvarUfs(ufs);
		return uf;		
	}
}
