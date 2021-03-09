package br.gov.prefeitura.mimg.resourse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.event.RecursoCriadoEvent;
import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.RegiaoRepository;
import br.gov.prefeitura.mimg.repository.regiao.filter.RegiaoFilter;
import br.gov.prefeitura.mimg.service.RegiaoService;
import br.gov.prefeitura.mimg.service.UfService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/regioes")
public class RegiaoResource {
	
	@Autowired
	private RegiaoRepository  regiaoRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private RegiaoService regiaoService;
	@Autowired
	private UfResource ufResource;
	@Autowired
	private MesorregiaoResource mesoRegiaoResource;
	@Autowired
	private MicrorregiaoResource microRegiaoResource;
	@Autowired
	private RegiaoIntermediariaResource regiaoIntermediariaResource;
	@Autowired
	private RegiaoImediataResource regiaoImediataResource;
	@Autowired
	private MunicipioResource municipioResource;
	
	
	
	
	@GetMapping
	public Page<Regiao> pesquisar(RegiaoFilter regiaoFilter,Pageable pageable){
		return regiaoRepository.filtrar(regiaoFilter,pageable);
	}
	

	@PostMapping
	public ResponseEntity<Regiao> criar(@Valid @RequestBody Regiao regiao, HttpServletResponse response) {
		Regiao regiaoSalvo = regiaoRepository.save(regiao);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, regiaoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(regiaoSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Regiao> buscarPorId(@PathVariable Integer id) {
		Optional<Regiao> regiao = regiaoRepository.findById(id);		
		return regiao != null && regiao.get() != null ? ResponseEntity.ok(regiao.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		regiaoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Regiao> atualizar(@PathVariable Integer id, @Valid @RequestBody Regiao regiao) {		
		Regiao regiaoSalvo = regiaoService.atualizar(id, regiao);	
		return ResponseEntity.ok(regiaoSalvo);		
	}
	
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
