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

import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.model.Municipio;
import br.gov.prefeitura.mimg.repository.MunicipioRepository;
import br.gov.prefeitura.mimg.repository.microrregiao.filter.MicrorregiaoFilter;
import br.gov.prefeitura.mimg.repository.municipio.filter.MunicipioFilter;
import br.gov.prefeitura.mimg.service.MunicipioService;

@RestController
@RequestMapping("/municipios")
public class MunicipioResource {
	
	@Autowired
	private MunicipioRepository  municipioRepository;
	
	@Autowired
	private MunicipioService  municipioService;
	
/*	@GetMapping
	public List<Municipio> listar(){
		return municipioRepository.findAll();
	}
	*/

	@GetMapping
	public Page<Municipio> pesquisar(MunicipioFilter municipioFilter,Pageable pageable){
		return municipioRepository.filtrar(municipioFilter,pageable);
	}
	
	
	@RequestMapping( value ="/ibge/{id}", method = RequestMethod.GET)
	public ResponseEntity<Municipio[]> pesquisarIbge(@PathVariable String id){
					
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("mesorregioes")
				.queryParam(id)
				.build();
		
		String NovoCaminho   = uri.toUriString().replace('-', '/');
		String caminho = NovoCaminho.replace("?","/");		
		
		ResponseEntity<Municipio[]> municipio = restTemplate.getForEntity(caminho, Municipio[].class);
		
		List<Municipio> municipios = Arrays.asList(municipio.getBody());
		
		municipioService.salvarMunicipio(municipios);
		return municipio;		
	} 
	
}
