package br.gov.prefeitura.msc.resourse;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import br.gov.prefeitura.msc.model.Sexo;
import br.gov.prefeitura.msc.repository.SexoRepository;
import br.gov.prefeitura.msc.repository.sexo.filter.SexoFilter;
import br.gov.prefeitura.msc.service.SexoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sexos")
public class SexoResource {
	
	@Autowired
	private SexoRepository  			sexoRepository;
	
	@Autowired
	private ApplicationEventPublisher 	publisher;
	
	@Autowired
	private SexoService           		sexoService;
	
	
//	@GetMapping
//	public Page<Sexo> pesquisar(SexoFilter sexoFilter,Pageable pageable){
//		return sexoRepository.filtrar(sexoFilter,pageable);
//	}
	
	@GetMapping
	public List<Sexo> pesquisar(SexoFilter sexoFilter){
		return sexoRepository.filtrar(sexoFilter);
	}
	
	@PostMapping
	public ResponseEntity<Sexo> criar(@Valid @RequestBody Sexo sexo, HttpServletResponse response) {
		Sexo sexoSalvo = sexoRepository.save(sexo);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, sexoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(sexoSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Sexo> buscarPorId(@PathVariable Integer id) {
		Optional<Sexo> sexo = sexoRepository.findById(id);		
		return sexo != null && sexo.get() != null ? ResponseEntity.ok(sexo.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		sexoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Sexo> atualizar(@PathVariable Integer id, @Valid @RequestBody Sexo sexo) {		
		Sexo sexoSalvo = sexoService.atualizar(id, sexo);	
		return ResponseEntity.ok(sexoSalvo);		
	}
	
}
