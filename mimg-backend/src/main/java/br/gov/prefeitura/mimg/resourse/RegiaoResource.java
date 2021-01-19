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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.event.RecursoCriadoEvent;
import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.repository.RegiaoRepository;
import br.gov.prefeitura.mimg.repository.regiao.filter.RegiaoFilter;
import br.gov.prefeitura.mimg.service.RegiaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/regioes")
public class RegiaoResource {
	
	@Autowired
	private RegiaoRepository  regiaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private RegiaoService           regiaoService;
	
	
	
	
	@GetMapping
	public Page<Regiao> pesquisar(RegiaoFilter regiaoFilter,Pageable pageable){
		return regiaoRepository.filtrar(regiaoFilter,pageable);
	}
	
//	@GetMapping
//	public List<Regiao> pesquisar(RegiaoFilter regiaoFilter){
//		return regiaoRepository.filtrar(regiaoFilter);
//	}
	
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
	
	@GetMapping("/ibge/{id}")
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
		
		for (Regiao regiao2 : regioes) {
			System.out.println("Resultado da Chamada REST: {"+ regiao2.getId() +","+regiao2.getNome()+","+regiao2.getSigla()+"}");
		}	     
		return regiao;		
	}    
}
