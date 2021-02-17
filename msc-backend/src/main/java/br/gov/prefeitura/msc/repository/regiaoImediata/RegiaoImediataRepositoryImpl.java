package br.gov.prefeitura.msc.repository.regiaoImediata;

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

import br.gov.prefeitura.msc.model.RegiaoImediata;
import br.gov.prefeitura.msc.repository.regiaoImediata.filter.RegiaoImediataFilter;

public class RegiaoImediataRepositoryImpl implements RegiaoImediataRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<RegiaoImediata> filtrar(RegiaoImediataFilter regiaoImediataFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RegiaoImediata> criteria = builder.createQuery(RegiaoImediata.class);		
		Root<RegiaoImediata> root = criteria.from(RegiaoImediata.class);
		
		Predicate[] predicates = criarRestricoes(regiaoImediataFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<RegiaoImediata> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<RegiaoImediata> filtrar(RegiaoImediataFilter regiaoImediataFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RegiaoImediata> criteria = builder.createQuery(RegiaoImediata.class);		
		Root<RegiaoImediata> root = criteria.from(RegiaoImediata.class);
		
		Predicate[] predicates = criarRestricoes(regiaoImediataFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<RegiaoImediata> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<RegiaoImediata>(query.getResultList(), pageable, total(regiaoImediataFilter));
	}

	private Predicate[] criarRestricoes(RegiaoImediataFilter regiaoImediataFilter, CriteriaBuilder builder, Root<RegiaoImediata> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(regiaoImediataFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + regiaoImediataFilter.getNome().toLowerCase() + "%"));
		}
		
		if(regiaoImediataFilter.getSigla() != null) {
			predicates.add(builder.like(builder.lower(root.get("sigla")),"%" + regiaoImediataFilter.getSigla().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<RegiaoImediata> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(RegiaoImediataFilter regiaoImediataFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<RegiaoImediata> root = criteria.from(RegiaoImediata.class);
		
		Predicate[] predicates = criarRestricoes(regiaoImediataFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
