package br.gov.prefeitura.mimg.repository.regiao;

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

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.repository.regiao.filter.RegiaoFilter;

public class RegiaoRepositoryImpl implements RegiaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Regiao> filtrar(RegiaoFilter regiaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Regiao> criteria = builder.createQuery(Regiao.class);		
		Root<Regiao> root = criteria.from(Regiao.class);
		
		Predicate[] predicates = criarRestricoes(regiaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Regiao> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Regiao> filtrar(RegiaoFilter regiaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Regiao> criteria = builder.createQuery(Regiao.class);		
		Root<Regiao> root = criteria.from(Regiao.class);
		
		Predicate[] predicates = criarRestricoes(regiaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Regiao> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Regiao>(query.getResultList(), pageable, total(regiaoFilter));
	}

	private Predicate[] criarRestricoes(RegiaoFilter regiaoFilter, CriteriaBuilder builder, Root<Regiao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(regiaoFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + regiaoFilter.getNome().toLowerCase() + "%"));
		}
		
		if(regiaoFilter.getSigla() != null) {
			predicates.add(builder.like(builder.lower(root.get("sigla")),"%" + regiaoFilter.getSigla().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Regiao> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(RegiaoFilter regiaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Regiao> root = criteria.from(Regiao.class);
		
		Predicate[] predicates = criarRestricoes(regiaoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
