package br.gov.prefeitura.seguranca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.prefeitura.seguranca.model.ERole;
import br.gov.prefeitura.seguranca.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
