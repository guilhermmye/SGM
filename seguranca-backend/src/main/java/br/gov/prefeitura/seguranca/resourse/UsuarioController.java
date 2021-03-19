package br.gov.prefeitura.seguranca.resourse;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.seguranca.model.Usuario;
import br.gov.prefeitura.seguranca.repository.usuario.UsuarioRepository;
import br.gov.prefeitura.seguranca.repository.usuario.filter.UsuarioFilter;
import br.gov.prefeitura.seguranca.service.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private UsuarioService  usuarioService;


	@GetMapping
	public List<Usuario> pesquisar(UsuarioFilter usuarioFilter){
		return userRepository.filtrar(usuarioFilter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
		Optional<Usuario> usuario = userRepository.findById(id);		
		return usuario != null && usuario.get() != null ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {		
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);	
		return ResponseEntity.ok(usuarioSalvo);		
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioService.cadastrar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);		
	}
	
}
