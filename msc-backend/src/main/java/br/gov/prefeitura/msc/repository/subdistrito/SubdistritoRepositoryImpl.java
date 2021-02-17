package br.gov.prefeitura.msc.repository.subdistrito;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.gov.prefeitura.msc.model.Subdistrito;
import br.gov.prefeitura.msc.repository.subdistrito.filter.SubdistritoFilter;

public class SubdistritoRepositoryImpl implements SubdistritoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Subdistrito> filtrar(SubdistritoFilter subdistritoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Subdistrito> criteria = builder.createQuery(Subdistrito.class);		
		Root<Subdistrito> root = criteria.from(Subdistrito.class);
		
		Predicate[] predicates = criarRestricoes(subdistritoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Subdistrito> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Subdistrito> filtrar(SubdistritoFilter subdistritoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Subdistrito> criteria = builder.createQuery(Subdistrito.class);		
		Root<Subdistrito> root = criteria.from(Subdistrito.class);
		
		Predicate[] predicates = criarRestricoes(subdistritoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Subdistrito> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Subdistrito>(query.getResultList(), pageable, total(subdistritoFilter));
	}

	private Predicate[] criarRestricoes(SubdistritoFilter subdistritoFilter, CriteriaBuilder builder, Root<Subdistrito> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(subdistritoFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + subdistritoFilter.getNome().toLowerCase() + "%"));
		}
			
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Subdistrito> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(SubdistritoFilter subdistritoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Subdistrito> root = criteria.from(Subdistrito.class);
		
		Predicate[] predicates = criarRestricoes(subdistritoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
