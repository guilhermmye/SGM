package br.gov.prefeitura.seguranca.repository.usuario;

import java.util.List;

import br.gov.prefeitura.seguranca.model.Usuario;
import br.gov.prefeitura.seguranca.repository.usuario.filter.UsuarioFilter;

public interface UsuarioRepositoryQuery {	
	List<Usuario> filtrar(UsuarioFilter usuarioFilter);
}
