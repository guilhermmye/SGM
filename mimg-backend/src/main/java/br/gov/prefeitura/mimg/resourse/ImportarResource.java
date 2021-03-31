package br.gov.prefeitura.mimg.resourse;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.service.RegiaoService;

@RestController
@RequestMapping("/importar")
public class ImportarResource {
	
	@Autowired
	private RegiaoService 				regiaoService;
	
	@Autowired
	private UfResource					ufResource;
	
	@Autowired
	private MesorregiaoResource 		mesoRegiaoResource;
	
	@Autowired
	private MicrorregiaoResource 		microRegiaoResource;
	
	@Autowired
	private RegiaoIntermediariaResource regiaoIntermediariaResource;
	
	@Autowired
	private RegiaoImediataResource 		regiaoImediataResource;
	
	@Autowired
	private MunicipioResource 			municipioResource;
	
	@RequestMapping( value ="/ibge/{id}", method = RequestMethod.GET)
	public ResponseEntity<Regiao[]> pesquisarIbge(@PathVariable String id){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("regioes")
				.queryParam(id)
				.build();
		
		ResponseEntity<Regiao[]> regiao = restTemplate.getForEntity(uri.toUriString(), Regiao[].class);
		
		List<Regiao> regioes = Arrays.asList(regiao.getBody());
		
	
		regiaoService.salvarRegioes(regioes);
		return regiao;		
	} 
	
	@RequestMapping( value ="/importar/{id}", method = RequestMethod.GET)
	public void pesquisarDados(@PathVariable String id){
		
		pesquisarRegioes(id);
		pesquisarUf();
		pesquisarMesoRegiao();
		pesquisarMicroRegiaoResource();
	    pesquisarRegiaoIntermediariaResource();
		pesquisarRegiaoImediataResource();
		pesquisarMunicipioResource();
		
	} 
	
	public void pesquisarRegioes(@PathVariable String id)
	{
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades")
				.path("regioes")
				.queryParam(id)
				.build();
		
		ResponseEntity<Regiao[]> regiao = restTemplate.getForEntity(uri.toUriString(), Regiao[].class);
		
		List<Regiao> regioes = Arrays.asList(regiao.getBody());
		
	
		regiaoService.salvarRegioes(regioes);
		
		
	}
	public void pesquisarUf()
	{
		ufResource.pesquisarUfIbge();
	}
	
	public void pesquisarMesoRegiao()
	{
		mesoRegiaoResource.pesquisarMesoRegiaoIbge();
	}
	public void pesquisarMicroRegiaoResource()
	{
		microRegiaoResource.pesquisarMicroRegiaoIbge();
	}	
	
	public void pesquisarRegiaoIntermediariaResource()
	{
		regiaoIntermediariaResource.pesquisarRegiaoIntermediariaIbge();
	}
		
	public void pesquisarRegiaoImediataResource()
	{
		regiaoImediataResource.pesquisarRegiaoImediataIbge();
	}
	public void pesquisarMunicipioResource()
	{
		municipioResource.pesquisarMunicipioIbge();
	}
	
}
