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
	private RegiaoRepository  			regiaoRepository;
	
	@Autowired
	private ApplicationEventPublisher 	publisher;
	
	@Autowired
	private RegiaoService 				regiaoService;

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
	
}
