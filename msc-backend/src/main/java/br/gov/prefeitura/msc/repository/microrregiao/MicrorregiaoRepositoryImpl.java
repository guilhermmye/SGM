package br.gov.prefeitura.msc.repository.microrregiao;

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

import br.gov.prefeitura.msc.model.Microrregiao;
import br.gov.prefeitura.msc.repository.microrregiao.filter.MicrorregiaoFilter;

public class MicrorregiaoRepositoryImpl implements MicrorregiaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Microrregiao> filtrar(MicrorregiaoFilter microrregiaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Microrregiao> criteria = builder.createQuery(Microrregiao.class);		
		Root<Microrregiao> root = criteria.from(Microrregiao.class);
		
		Predicate[] predicates = criarRestricoes(microrregiaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Microrregiao> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Microrregiao> filtrar(MicrorregiaoFilter microrregiaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Microrregiao> criteria = builder.createQuery(Microrregiao.class);		
		Root<Microrregiao> root = criteria.from(Microrregiao.class);
		
		Predicate[] predicates = criarRestricoes(microrregiaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Microrregiao> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Microrregiao>(query.getResultList(), pageable, total(microrregiaoFilter));
	}

	private Predicate[] criarRestricoes(MicrorregiaoFilter microrregiaoFilter, CriteriaBuilder builder, Root<Microrregiao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(microrregiaoFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + microrregiaoFilter.getNome().toLowerCase() + "%"));
		}
		
		if(microrregiaoFilter.getSigla() != null) {
			predicates.add(builder.like(builder.lower(root.get("sigla")),"%" + microrregiaoFilter.getSigla().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Microrregiao> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(MicrorregiaoFilter microrregiaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Microrregiao> root = criteria.from(Microrregiao.class);
		
		Predicate[] predicates = criarRestricoes(microrregiaoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
