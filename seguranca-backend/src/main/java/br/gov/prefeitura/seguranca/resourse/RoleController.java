package br.gov.prefeitura.seguranca.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.seguranca.model.Role;
import br.gov.prefeitura.seguranca.repository.role.RoleRepository;
import br.gov.prefeitura.seguranca.repository.role.filter.RoleFilter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/roles")
public class RoleController {


	@Autowired
	private RoleRepository roleRepository;


	@GetMapping
	public List<Role> pesquisar(RoleFilter roleFilter){
		return roleRepository.filtrar(roleFilter);
	}
		
}
