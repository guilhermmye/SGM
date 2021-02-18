package br.gov.prefeitura.msc.repository.sexo;

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

import br.gov.prefeitura.msc.model.Sexo;
import br.gov.prefeitura.msc.repository.sexo.filter.SexoFilter;

public class SexoRepositoryImpl implements SexoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Sexo> filtrar(SexoFilter sexoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Sexo> criteria = builder.createQuery(Sexo.class);		
		Root<Sexo> root = criteria.from(Sexo.class);
		
		Predicate[] predicates = criarRestricoes(sexoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Sexo> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Sexo> filtrar(SexoFilter sexoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Sexo> criteria = builder.createQuery(Sexo.class);		
		Root<Sexo> root = criteria.from(Sexo.class);
		
		Predicate[] predicates = criarRestricoes(sexoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Sexo> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Sexo>(query.getResultList(), pageable, total(sexoFilter));
	}

	private Predicate[] criarRestricoes(SexoFilter sexoFilter, CriteriaBuilder builder, Root<Sexo> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(sexoFilter.getDescricao() != null) {
			predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + sexoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Sexo> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(SexoFilter sexoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Sexo> root = criteria.from(Sexo.class);
		
		Predicate[] predicates = criarRestricoes(sexoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
