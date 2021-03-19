package br.gov.prefeitura.seguranca.repository.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.prefeitura.seguranca.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>,UsuarioRepositoryQuery {
	Optional<Usuario> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
