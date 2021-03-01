package br.gov.prefeitura.msc.resourse;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.msc.event.RecursoCriadoEvent;
import br.gov.prefeitura.msc.model.Cidadao;
import br.gov.prefeitura.msc.repository.CidadaoRepository;
import br.gov.prefeitura.msc.repository.cidadao.filter.CidadaoFilter;
import br.gov.prefeitura.msc.service.CidadaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cidadaos")
public class CidadaoResource {
	
	@Autowired
	private CidadaoRepository  			cidadaoRepository;
	
	@Autowired
	private ApplicationEventPublisher 	publisher;
	
	@Autowired
	private CidadaoService           	cidadaoService;
	
	@GetMapping
	public Page<Cidadao> pesquisar(CidadaoFilter cidadaoFilter,Pageable pageable){
		return cidadaoRepository.filtrar(cidadaoFilter,pageable);
	}
	
//	@GetMapping
//	public List<Cidadao> pesquisar(cidadaoFilter cidadaoFilter){
//		return cidadaoRepository.filtrar(cidadaoFilter);
//	}
	
	@PostMapping
	public ResponseEntity<Cidadao> criar(@Valid @RequestBody Cidadao cidadao, HttpServletResponse response) {
		Cidadao cidadaoSalvo = cidadaoService.cadastrar(cidadao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadaoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadaoSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidadao> buscarPorId(@PathVariable Integer id) {
		Optional<Cidadao> cidadao = cidadaoRepository.findById(id);		
		return cidadao != null && cidadao.get() != null ? ResponseEntity.ok(cidadao.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		cidadaoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cidadao> atualizar(@PathVariable Integer id, @Valid @RequestBody Cidadao cidadao) {		
		Cidadao cidadaoSalvo = cidadaoService.atualizar(id, cidadao);	
		return ResponseEntity.ok(cidadaoSalvo);		
	}
	
}
