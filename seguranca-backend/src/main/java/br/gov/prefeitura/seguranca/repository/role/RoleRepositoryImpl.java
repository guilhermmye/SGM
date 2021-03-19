package br.gov.prefeitura.seguranca.repository.role;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.prefeitura.seguranca.model.Role;
import br.gov.prefeitura.seguranca.repository.role.filter.RoleFilter;

public class RoleRepositoryImpl implements RoleRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Role> filtrar(RoleFilter roleFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Role> criteria = builder.createQuery(Role.class);		
		Root<Role> root = criteria.from(Role.class);
		
		Predicate[] predicates = criarRestricoes(roleFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Role> query = manager.createQuery(criteria);		
		return query.getResultList();
	}
	

	private Predicate[] criarRestricoes(RoleFilter roleFilter, CriteriaBuilder builder, Root<Role> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(roleFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + roleFilter.getNome().toLowerCase() + "%"));
		}
		
		if(roleFilter.getDescricao() != null) {
			predicates.add(builder.like(builder.lower(root.get("email")),"%" + roleFilter.getDescricao().toLowerCase() + "%"));
		}
		
	
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
