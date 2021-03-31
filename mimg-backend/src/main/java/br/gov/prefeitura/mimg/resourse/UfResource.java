package br.gov.prefeitura.mimg.resourse;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.UfRepository;
import br.gov.prefeitura.mimg.repository.uf.filter.UfFilter;
import br.gov.prefeitura.mimg.service.UfService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ufs")
public class UfResource {
	
	@Autowired
	private UfRepository  	ufRepository;
	
	@Autowired
	private UfService  		ufService;
	
	private final static String PARAMETROS = "11|12|13|14|15|16|17|21|22|23|24|25|26|27|28|29|31|32|33|35|41|42|43|50|51|52|53";
	
	@RequestMapping( value ="/listar", method = RequestMethod.GET)
	public List<Uf> listar(){
		return ufRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Uf> pesquisar(UfFilter ufFilter,Pageable pageable){
		return ufRepository.filtrar(ufFilter,pageable);
	}
	
	public void pesquisarUfIbge(){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("estados")
				.queryParam(PARAMETROS)
				.build();
		String caminho = uri.toString().replace("?","/");
		ResponseEntity<Uf[]> uf = restTemplate.getForEntity(caminho, Uf[].class);
		
		List<Uf> ufs = Arrays.asList(uf.getBody());
			
		ufService.salvarUfs(ufs);		
	}
}
