package br.gov.prefeitura.mimg.repository.regiaoIntermediaria;

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

import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.repository.regiaoIntermediaria.filter.RegiaoIntermediariaFilter;

public class RegiaoIntermediariaRepositoryImpl implements RegiaoIntermediariaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<RegiaoIntermediaria> filtrar(RegiaoIntermediariaFilter regiaoIntermediariaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RegiaoIntermediaria> criteria = builder.createQuery(RegiaoIntermediaria.class);		
		Root<RegiaoIntermediaria> root = criteria.from(RegiaoIntermediaria.class);
		
		Predicate[] predicates = criarRestricoes(regiaoIntermediariaFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<RegiaoIntermediaria> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<RegiaoIntermediaria> filtrar(RegiaoIntermediariaFilter regiaoIntermediariaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RegiaoIntermediaria> criteria = builder.createQuery(RegiaoIntermediaria.class);		
		Root<RegiaoIntermediaria> root = criteria.from(RegiaoIntermediaria.class);
		
		Predicate[] predicates = criarRestricoes(regiaoIntermediariaFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<RegiaoIntermediaria> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<RegiaoIntermediaria>(query.getResultList(), pageable, total(regiaoIntermediariaFilter));
	}

	private Predicate[] criarRestricoes(RegiaoIntermediariaFilter regiaoIntermediariaFilter, CriteriaBuilder builder, Root<RegiaoIntermediaria> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(regiaoIntermediariaFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + regiaoIntermediariaFilter.getNome().toLowerCase() + "%"));
		}
		
		if(regiaoIntermediariaFilter.getSigla() != null) {
			predicates.add(builder.like(builder.lower(root.get("sigla")),"%" + regiaoIntermediariaFilter.getSigla().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<RegiaoIntermediaria> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(RegiaoIntermediariaFilter regiaoIntermediariaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<RegiaoIntermediaria> root = criteria.from(RegiaoIntermediaria.class);
		
		Predicate[] predicates = criarRestricoes(regiaoIntermediariaFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
