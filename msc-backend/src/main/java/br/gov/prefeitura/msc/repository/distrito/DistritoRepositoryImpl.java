package br.gov.prefeitura.msc.repository.distrito;

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

import br.gov.prefeitura.msc.model.Distrito;
import br.gov.prefeitura.msc.repository.distrito.filter.DistritoFilter;

public class DistritoRepositoryImpl implements DistritoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Distrito> filtrar(DistritoFilter distritoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Distrito> criteria = builder.createQuery(Distrito.class);		
		Root<Distrito> root = criteria.from(Distrito.class);
		
		Predicate[] predicates = criarRestricoes(distritoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Distrito> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Distrito> filtrar(DistritoFilter distritoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Distrito> criteria = builder.createQuery(Distrito.class);		
		Root<Distrito> root = criteria.from(Distrito.class);
		
		Predicate[] predicates = criarRestricoes(distritoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Distrito> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Distrito>(query.getResultList(), pageable, total(distritoFilter));
	}

	private Predicate[] criarRestricoes(DistritoFilter distritoFilter, CriteriaBuilder builder, Root<Distrito> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(distritoFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + distritoFilter.getNome().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Distrito> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(DistritoFilter distritoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Distrito> root = criteria.from(Distrito.class);
		
		Predicate[] predicates = criarRestricoes(distritoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
