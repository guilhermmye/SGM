package br.gov.prefeitura.msc.repository.cidadao;

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

import br.gov.prefeitura.msc.model.Cidadao;
import br.gov.prefeitura.msc.repository.pessoa.filter.CidadaoFilter;

public class CidadaoRepositoryImpl implements CidadaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidadao> filtrar(CidadaoFilter cidadaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidadao> criteria = builder.createQuery(Cidadao.class);		
		Root<Cidadao> root = criteria.from(Cidadao.class);
		
		Predicate[] predicates = criarRestricoes(cidadaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Cidadao> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Cidadao> filtrar(CidadaoFilter cidadaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidadao> criteria = builder.createQuery(Cidadao.class);		
		Root<Cidadao> root = criteria.from(Cidadao.class);
		
		Predicate[] predicates = criarRestricoes(cidadaoFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Cidadao> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Cidadao>(query.getResultList(), pageable, total(cidadaoFilter));
	}

	private Predicate[] criarRestricoes(CidadaoFilter cidadaoFilter, CriteriaBuilder builder, Root<Cidadao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(cidadaoFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + cidadaoFilter.getNome().toLowerCase() + "%"));
		}
		
		if(cidadaoFilter.getCpfCnpj() != null) {
			predicates.add(builder.equal(root.get("cpfCnpj"),cidadaoFilter.getCpfCnpj().replace(".","").replace("-","").replace("/","")));
		}
		
		if(cidadaoFilter.getEmail() != null) {
			predicates.add(builder.like(builder.lower(root.get("email")),"%" + cidadaoFilter.getEmail().toLowerCase() + "%"));
		}
		
		if(cidadaoFilter.getTelefone() != null) {
			predicates.add(builder.like(builder.lower(root.get("telefone")),"%" + cidadaoFilter.getTelefone().toLowerCase() + "%"));
		}
		
		if(cidadaoFilter.getDataNascimento() != null) {
			predicates.add(builder.equal(root.get("dataNascimento"),cidadaoFilter.getDataNascimento()));
		}
		
		if(cidadaoFilter.getEndereco() != null) {
			predicates.add(builder.like(builder.lower(root.get("endereco")),"%" + cidadaoFilter.getEndereco().toLowerCase() + "%"));
		}
		
		if(cidadaoFilter.getCep() != null) {
			predicates.add(builder.equal(root.get("cep"),cidadaoFilter.getCep()));
		}
		
		if(cidadaoFilter.getMunicipioId() != null) {
			predicates.add(builder.equal(root.get("municipioId"),cidadaoFilter.getId()));
		}
		
		if(cidadaoFilter.getNumero() != null) {
			predicates.add(builder.like(builder.lower(root.get("numero")),"%" + cidadaoFilter.getNome().toLowerCase() + "%"));
		}
		
//		if(cidadaoFilter.getSexo() != null) {
//			predicates.add(builder.like(builder.lower(root.get("endereco")),"%" + cidadaoFilter.getNome().toLowerCase() + "%"));
//		}
//		
//		if(cidadaoFilter.getIdTipoPessoa() != null) {
//			predicates.add(builder.equal(root.get("idTipoPessoa"),cidadaoFilter.getIdTipoPessoa()));
//		}
		
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Cidadao> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(CidadaoFilter cidadaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Cidadao> root = criteria.from(Cidadao.class);
		
		Predicate[] predicates = criarRestricoes(cidadaoFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
