package br.gov.prefeitura.mimg.resourse;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.event.RecursoCriadoEvent;
import br.gov.prefeitura.mimg.model.Distrito;
import br.gov.prefeitura.mimg.repository.DistritoRepository;
import br.gov.prefeitura.mimg.repository.distrito.filter.DistritoFilter;
import br.gov.prefeitura.mimg.service.DistritoService;

@RestController
@RequestMapping("/distritos")
public class DistritoResource {
	
	@Autowired
	private DistritoRepository        distritoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private DistritoService           distritoService;
	
	@GetMapping
	public Page<Distrito> pesquisar(DistritoFilter distritoFilter,Pageable pageable){
		return distritoRepository.filtrar(distritoFilter,pageable);
	}
	
//	@GetMapping
//	public List<Distrito> pesquisar(DistritoFilter distritoFilter){
//		return distritoRepository.filtrar(distritoFilter);
//	}
	
	@PostMapping
	public ResponseEntity<Distrito> criar(@Valid @RequestBody Distrito distrito, HttpServletResponse response) {
		Distrito distritoSalvo = distritoRepository.save(distrito);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, distritoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(distritoSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Distrito> buscarPorId(@PathVariable Integer id) {
		Optional<Distrito> distrito = distritoRepository.findById(id);		
		return distrito != null && distrito.get() != null ? ResponseEntity.ok(distrito.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		distritoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Distrito> atualizar(@PathVariable Integer id, @Valid @RequestBody Distrito distrito) {		
		Distrito distritoSalvo = distritoService.atualizar(id, distrito);	
		return ResponseEntity.ok(distritoSalvo);		
	}
	
	@PutMapping("/{id}/nome")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Distrito> atualizarNome(@PathVariable Integer id, @Valid @RequestBody String nome) {		
		Distrito distritoSalvo = distritoService.atualizarNome(id, nome);	
		return ResponseEntity.ok(distritoSalvo);		
	}
	
	
	
}
