package br.gov.prefeitura.seguranca.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.seguranca.model.Usuario;
import br.gov.prefeitura.seguranca.repository.usuario.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository      usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public Usuario atualizar(Integer id,Usuario usuario) {		
		Usuario usuarioSalvo = buscarPessoaPorId(id);
		BeanUtils.copyProperties(usuario,usuarioSalvo,"id");		
		return usuarioRepository.save(usuarioSalvo);		
	}
	
	@Transactional
	public Usuario cadastrar(Usuario usuario) {	
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);		
	}
	
	private Usuario buscarPessoaPorId(Integer id) {
		Optional<Usuario> cidadaoSalvo = usuarioRepository.findById(id);		
		if(cidadaoSalvo == null || cidadaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cidadaoSalvo.get();
	}
		
}
