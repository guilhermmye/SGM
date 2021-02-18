package br.gov.prefeitura.msc.repository.tipoPessoa;

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

import br.gov.prefeitura.msc.model.TipoPessoa;
import br.gov.prefeitura.msc.repository.tipoPessoa.filter.TipoPessoaFilter;

public class TipoPessoaRepositoryImpl implements TipoPessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<TipoPessoa> filtrar(TipoPessoaFilter tipoPessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TipoPessoa> criteria = builder.createQuery(TipoPessoa.class);		
		Root<TipoPessoa> root = criteria.from(TipoPessoa.class);
		
		Predicate[] predicates = criarRestricoes(tipoPessoaFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<TipoPessoa> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<TipoPessoa> filtrar(TipoPessoaFilter tipoPessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TipoPessoa> criteria = builder.createQuery(TipoPessoa.class);		
		Root<TipoPessoa> root = criteria.from(TipoPessoa.class);
		
		Predicate[] predicates = criarRestricoes(tipoPessoaFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<TipoPessoa> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<TipoPessoa>(query.getResultList(), pageable, total(tipoPessoaFilter));
	}

	private Predicate[] criarRestricoes(TipoPessoaFilter tipoPessoaFilter, CriteriaBuilder builder, Root<TipoPessoa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(tipoPessoaFilter.getDescricao() != null) {
			predicates.add(builder.like(builder.lower(root.get("descricao")),"%" + tipoPessoaFilter.getDescricao().toLowerCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<TipoPessoa> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(TipoPessoaFilter tipoPessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<TipoPessoa> root = criteria.from(TipoPessoa.class);
		
		Predicate[] predicates = criarRestricoes(tipoPessoaFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
