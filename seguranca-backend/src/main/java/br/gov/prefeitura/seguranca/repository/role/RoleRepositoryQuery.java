package br.gov.prefeitura.seguranca.repository.role;

import java.util.List;

import br.gov.prefeitura.seguranca.model.Role;
import br.gov.prefeitura.seguranca.repository.role.filter.RoleFilter;

public interface RoleRepositoryQuery {	
	List<Role> filtrar(RoleFilter roleFilter);
}
