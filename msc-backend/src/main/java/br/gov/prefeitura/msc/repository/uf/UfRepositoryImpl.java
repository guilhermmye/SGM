package br.gov.prefeitura.msc.repository.uf;

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

import br.gov.prefeitura.msc.model.Uf;
import br.gov.prefeitura.msc.repository.uf.filter.UfFilter;

public class UfRepositoryImpl implements UfRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Uf> filtrar(UfFilter ufFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Uf> criteria = builder.createQuery(Uf.class);		
		Root<Uf> root = criteria.from(Uf.class);
		
		Predicate[] predicates = criarRestricoes(ufFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Uf> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Uf> filtrar(UfFilter ufFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Uf> criteria = builder.createQuery(Uf.class);		
		Root<Uf> root = criteria.from(Uf.class);
		
		Predicate[] predicates = criarRestricoes(ufFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Uf> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Uf>(query.getResultList(), pageable, total(ufFilter));
	}

	private Predicate[] criarRestricoes(UfFilter ufFilter, CriteriaBuilder builder, Root<Uf> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(ufFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + ufFilter.getNome().toLowerCase() + "%"));
		}
		
		if(ufFilter.getSigla() != null) {
			predicates.add(builder.like(builder.lower(root.get("sigla")),"%" + ufFilter.getSigla().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Uf> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(UfFilter ufFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Uf> root = criteria.from(Uf.class);
		
		Predicate[] predicates = criarRestricoes(ufFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
