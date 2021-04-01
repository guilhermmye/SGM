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
import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.repository.MesorregiaoRepository;
import br.gov.prefeitura.mimg.repository.mesorregiao.filter.MesorregiaoFilter;
import br.gov.prefeitura.mimg.service.MesorregiaoService;

@RestController
@RequestMapping("/mesorregioes")
public class MesorregiaoResource {
	
	@Autowired
	private MesorregiaoRepository  		mesorregiaoRepository;
		
	@Autowired
	private ApplicationEventPublisher   publisher;
	
	@Autowired
	private MesorregiaoService          mesorregiaoService;
	
	@GetMapping
	public Page<Mesorregiao> pesquisar(MesorregiaoFilter mesorregiaoFilter,Pageable pageable){
		return mesorregiaoRepository.filtrar(mesorregiaoFilter,pageable);
	}

	@PostMapping
	public ResponseEntity<Mesorregiao> criar(@Valid @RequestBody Mesorregiao mesorregiao, HttpServletResponse response) {
		Mesorregiao mesorregiaoSalvo = mesorregiaoRepository.save(mesorregiao);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, mesorregiaoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(mesorregiaoSalvo);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mesorregiao> buscarPorId(@PathVariable Integer id) {
		Optional<Mesorregiao> mesorregiao = mesorregiaoRepository.findById(id);		
		return mesorregiao != null && mesorregiao.get() != null ? ResponseEntity.ok(mesorregiao.get()) : ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		mesorregiaoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mesorregiao> atualizar(@PathVariable Integer id, @Valid @RequestBody Mesorregiao mesorregiao) {		
		Mesorregiao mesorregiaoSalvo = mesorregiaoService.atualizar(id, mesorregiao);	
		return ResponseEntity.ok(mesorregiaoSalvo);		
	}
			
}
