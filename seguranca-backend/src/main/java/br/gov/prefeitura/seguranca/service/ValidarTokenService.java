package br.gov.prefeitura.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.seguranca.util.JwtUtils;
import br.gov.prefeitura.seguranca.util.RetornoValidarTokenDTO;

@Service
public class ValidarTokenService {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	public RetornoValidarTokenDTO validacao(String token) {	
		RetornoValidarTokenDTO retorno = new RetornoValidarTokenDTO();
		retorno.setValido(jwtUtils.validateJwtToken(token));
		retorno.setUsuario(userDetailsService.loadUsername(jwtUtils.getUserNameFromJwtToken(token)));		
		return retorno;
	}
	

		
}
