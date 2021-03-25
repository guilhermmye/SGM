package br.gov.prefeitura.seguranca.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.seguranca.service.ValidarTokenService;
import br.gov.prefeitura.seguranca.util.RetornoValidarTokenDTO;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/validarToken")
public class ValidarTokenController {
	
	@Autowired
	private ValidarTokenService  validarTokenService;

	@GetMapping("/{token}")
	public ResponseEntity<?> validart(@PathVariable String token) {
		RetornoValidarTokenDTO retorno = validarTokenService.validacao(token);
		return ResponseEntity.ok(retorno);
	}
	

}
