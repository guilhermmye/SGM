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
import br.gov.prefeitura.msc.model.Pessoa;
import br.gov.prefeitura.msc.repository.PessoaRepository;
import br.gov.prefeitura.msc.repository.pessoa.filter.PessoaFilter;
import br.gov.prefeitura.msc.service.PessoaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository  			pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher 	publisher;
	
	@Autowired
	private PessoaService           	pessoaService;
	
	@GetMapping
	public Page<Pessoa> pesquisar(PessoaFilter pessoaFilter,Pageable pageable){
		return pessoaRepository.filtrar(pessoaFilter,pageable);
	}
	
//	@GetMapping
//	public List<Pessoa> pesquisar(PessoaFilter pessoaFilter){
//		return pessoaRepository.filtrar(pessoaFilter);
//	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalvo = pessoaRepository.save(pessoa);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Integer id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);		
		return pessoa != null && pessoa.get() != null ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		pessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {		
		Pessoa pessoaSalvo = pessoaService.atualizar(id, pessoa);	
		return ResponseEntity.ok(pessoaSalvo);		
	}
	
}
