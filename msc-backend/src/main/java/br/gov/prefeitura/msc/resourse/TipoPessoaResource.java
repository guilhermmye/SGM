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
import br.gov.prefeitura.msc.model.TipoPessoa;
import br.gov.prefeitura.msc.repository.TipoPessoaRepository;
import br.gov.prefeitura.msc.repository.tipoPessoa.filter.TipoPessoaFilter;
import br.gov.prefeitura.msc.service.TipoPessoaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tiposPessoas")
public class TipoPessoaResource {
	
	@Autowired
	private TipoPessoaRepository  		tipoPessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher 	publisher;
	
	@Autowired
	private TipoPessoaService           tipoPessoaService;
	
	@GetMapping
	public Page<TipoPessoa> pesquisar(TipoPessoaFilter tipoPessoaFilter,Pageable pageable){
		return tipoPessoaRepository.filtrar(tipoPessoaFilter,pageable);
	}
	
//	@GetMapping
//	public List<TipoPessoa> pesquisar(TipoPessoaFilter tipoPessoaFilter){
//		return tipoPessoaRepository.filtrar(tipoPessoaFilter);
//	}
	
	@PostMapping
	public ResponseEntity<TipoPessoa> criar(@Valid @RequestBody TipoPessoa tipoPessoa, HttpServletResponse response) {
		TipoPessoa tipoPessoaSalvo = tipoPessoaRepository.save(tipoPessoa);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoPessoaSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoPessoaSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoPessoa> buscarPorId(@PathVariable Integer id) {
		Optional<TipoPessoa> tipoPessoa = tipoPessoaRepository.findById(id);		
		return tipoPessoa != null && tipoPessoa.get() != null ? ResponseEntity.ok(tipoPessoa.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		tipoPessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoPessoa> atualizar(@PathVariable Integer id, @Valid @RequestBody TipoPessoa tipoPessoa) {		
		TipoPessoa tipoPessoaSalvo = tipoPessoaService.atualizar(id, tipoPessoa);	
		return ResponseEntity.ok(tipoPessoaSalvo);		
	}
	
}
