package br.gov.prefeitura.mimg.resourse;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Municipio;
import br.gov.prefeitura.mimg.repository.MunicipioRepository;
import br.gov.prefeitura.mimg.repository.municipio.filter.MunicipioFilter;
import br.gov.prefeitura.mimg.service.MunicipioService;

@RestController
@RequestMapping("/municipios")
public class MunicipioResource {
	
	@Autowired
	private MunicipioRepository  	municipioRepository;
	
	@Autowired
	private MunicipioService  		municipioService;
	
	private final static String PARAMETROS = "31/municipios";

	@GetMapping
	public Page<Municipio> pesquisar(MunicipioFilter municipioFilter,Pageable pageable){
		return municipioRepository.filtrar(municipioFilter,pageable);
	}
	
	public void pesquisarMunicipioIbge(){
					
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("estados")
				.queryParam(PARAMETROS)
				.build();
			
		String caminho = uri.toString().replace("?","/");				
		ResponseEntity<Municipio[]> municipio = restTemplate.getForEntity(caminho, Municipio[].class);		
		List<Municipio> municipios = Arrays.asList(municipio.getBody());		
		municipioService.salvarMunicipio(municipios);
	
	} 	
}
