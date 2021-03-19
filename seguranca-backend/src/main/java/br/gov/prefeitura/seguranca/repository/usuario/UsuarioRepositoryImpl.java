package br.gov.prefeitura.seguranca.repository.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.prefeitura.seguranca.model.Usuario;
import br.gov.prefeitura.seguranca.repository.usuario.filter.UsuarioFilter;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Usuario> filtrar(UsuarioFilter usuarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);		
		Root<Usuario> root = criteria.from(Usuario.class);
		
		Predicate[] predicates = criarRestricoes(usuarioFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Usuario> query = manager.createQuery(criteria);		
		return query.getResultList();
	}
	

	private Predicate[] criarRestricoes(UsuarioFilter usuarioFilter, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(usuarioFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + usuarioFilter.getNome().toLowerCase() + "%"));
		}
		
		if(usuarioFilter.getEmail() != null) {
			predicates.add(builder.like(builder.lower(root.get("email")),"%" + usuarioFilter.getEmail().toLowerCase() + "%"));
		}
		
	
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}
