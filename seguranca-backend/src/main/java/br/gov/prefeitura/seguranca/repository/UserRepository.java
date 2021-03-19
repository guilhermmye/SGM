package br.gov.prefeitura.seguranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.prefeitura.seguranca.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);

	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
