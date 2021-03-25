package br.gov.prefeitura.seguranca.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.seguranca.model.Usuario;
import br.gov.prefeitura.seguranca.service.UserDetailsServiceImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/validarToken")
public class ValidarTokenController {
	

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping("/{username}")
	public ResponseEntity<?> validar(@PathVariable String username) {
		Usuario usuario = userDetailsService.loadUsername(username);
		return ResponseEntity.ok(usuario);
	}
	

}
