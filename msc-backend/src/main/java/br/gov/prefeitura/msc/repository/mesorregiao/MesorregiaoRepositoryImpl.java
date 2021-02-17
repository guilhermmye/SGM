package br.gov.prefeitura.msc.repository.mesorregiao;

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

import br.gov.prefeitura.msc.model.Mesorregiao;
import br.gov.prefeitura.msc.repository.mesorregiao.filter.MesorregiaoFilter;

public class MesorregiaoRepositoryImpl implements MesorregiaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Mesorregiao> filtrar(MesorregiaoFilter mesorregiaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Mesorregiao> criteria = builder.createQuery(Mesorregiao.class);		
		Root<Mesorregiao> root = criteria.from(Mesorregiao.class);
		
		Predicate[] predicates = criarRestricoes(mesorregiaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Mesorregiao> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Mesorregiao> filtrar(MesorregiaoFilter mesorregiaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Mesorregiao> criteria = builder.createQuery(Mesorregiao.class);		
		Root<Mesorregiao> root = criteria.from(Mesorregiao.class);
		
		Predicate[] predicates = criarRestricoes(mesorregiaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Mesorregiao> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Mesorregiao>(query.getResultList(), pageable, total(mesorregiaoFilter));
	}

	private Predicate[] criarRestricoes(MesorregiaoFilter mesorregiaoFilter, CriteriaBuilder builder, Root<Mesorregiao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(mesorregiaoFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + mesorregiaoFilter.getNome().toLowerCase() + "%"));
		}
		
		if(mesorregiaoFilter.getSigla() != null) {
			predicates.add(builder.like(builder.lower(root.get("sigla")),"%" + mesorregiaoFilter.getSigla().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Mesorregiao> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(MesorregiaoFilter mesorregiaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Mesorregiao> root = criteria.from(Mesorregiao.class);
		
		Predicate[] predicates = criarRestricoes(mesorregiaoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
