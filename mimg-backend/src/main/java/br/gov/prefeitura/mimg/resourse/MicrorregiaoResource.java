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

import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.repository.MicrorregiaoRepository;
import br.gov.prefeitura.mimg.repository.microrregiao.filter.MicrorregiaoFilter;
import br.gov.prefeitura.mimg.service.MicrorregiaoService;

@RestController
@RequestMapping("/microrregioes")
public class MicrorregiaoResource {
	
	@Autowired
	private MicrorregiaoRepository  		microrregiaoRepository;
	
	@Autowired
	private MicrorregiaoService           	microrregiaoService;
	
	private final static String PARAMETROS = "3101|3102|3103|3104|3105|3106|3107|3108|3109|3110|3111|3112/microrregioes";
	
	
	@GetMapping
	public Page<Microrregiao> pesquisar(MicrorregiaoFilter microrregiaoFilter,Pageable pageable){
		return microrregiaoRepository.filtrar(microrregiaoFilter,pageable);
	}
			
	public void pesquisarMicroRegiaoIbge(){
					
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("mesorregioes")
				.queryParam(PARAMETROS)
				.build();
		
		String NovoCaminho   = uri.toUriString().replace('-', '/');
		String caminho = NovoCaminho.replace("?","/");		
		
		ResponseEntity<Microrregiao[]> microrregiao = restTemplate.getForEntity(caminho, Microrregiao[].class);
		
		List<Microrregiao> microrregioes = Arrays.asList(microrregiao.getBody());
		
		microrregiaoService.salvarMicrorregioes(microrregioes);			
	} 	
}
