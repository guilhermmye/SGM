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

import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.repository.RegiaoImediataRepository;
import br.gov.prefeitura.mimg.repository.regiaoImediata.filter.RegiaoImediataFilter;
import br.gov.prefeitura.mimg.repository.regiaoIntermediaria.filter.RegiaoIntermediariaFilter;
import br.gov.prefeitura.mimg.service.RegiaoImediataService;

@RestController
@RequestMapping("/regioesImediatas")
public class RegiaoImediataResource {
	
	@Autowired
	private RegiaoImediataRepository  regiaoImediataRepository;
	
	@Autowired
	private RegiaoImediataService  regiaoImediataService;
	
	final String PARAMETROS = "31/regioes-imediatas";
	
	/*@GetMapping
	public List<RegiaoImediata> listar(){
		return regiaoImediataRepository.findAll();
	}*/
	
	@GetMapping
	public Page<RegiaoImediata> pesquisar(RegiaoImediataFilter regiaoImediataFilter,Pageable pageable){
		return regiaoImediataRepository.filtrar(regiaoImediataFilter, pageable);
	}
	
	public void pesquisarRegiaoImediataIbge() {

		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades").path("estados").queryParam(PARAMETROS).build();
		String caminho = uri.toString().replace("?", "/");
		ResponseEntity<RegiaoImediata[]> regiaoImediata = restTemplate.getForEntity(caminho, RegiaoImediata[].class);
		

		List<RegiaoImediata> regiaoImediatas = Arrays.asList(regiaoImediata.getBody());

		regiaoImediataService.salvarRegiaoImediatas(regiaoImediatas);
		
	}
}
