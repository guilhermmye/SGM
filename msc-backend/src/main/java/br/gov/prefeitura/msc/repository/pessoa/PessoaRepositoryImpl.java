package br.gov.prefeitura.msc.repository.pessoa;

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

import br.gov.prefeitura.msc.model.Pessoa;
import br.gov.prefeitura.msc.repository.pessoa.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Pessoa> filtrar(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);		
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoaFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Pessoa> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	@Override
	public Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);		
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoaFilter,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Pessoa> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query,pageable);
		
		return new PageImpl<Pessoa>(query.getResultList(), pageable, total(pessoaFilter));
	}

	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(pessoaFilter.getNome() != null) {
			predicates.add(builder.like(builder.lower(root.get("nome")),"%" + pessoaFilter.getNome().toLowerCase() + "%"));
		}
		
		if(pessoaFilter.getCpfCnpj() != null) {
			predicates.add(builder.equal(root.get("cpfCnpj"),pessoaFilter.getCpfCnpj().replace(".","").replace("-","").replace("/","")));
		}
		
		if(pessoaFilter.getEmail() != null) {
			predicates.add(builder.like(builder.lower(root.get("email")),"%" + pessoaFilter.getEmail().toLowerCase() + "%"));
		}
		
		if(pessoaFilter.getTelefone() != null) {
			predicates.add(builder.like(builder.lower(root.get("telefone")),"%" + pessoaFilter.getTelefone().toLowerCase() + "%"));
		}
		
		if(pessoaFilter.getDataNascimento() != null) {
			predicates.add(builder.equal(root.get("dataNascimento"),pessoaFilter.getDataNascimento()));
		}
		
		if(pessoaFilter.getEndereco() != null) {
			predicates.add(builder.like(builder.lower(root.get("endereco")),"%" + pessoaFilter.getEndereco().toLowerCase() + "%"));
		}
		
		if(pessoaFilter.getCep() != null) {
			predicates.add(builder.equal(root.get("cep"),pessoaFilter.getCep()));
		}
		
		if(pessoaFilter.getMunicipioId() != null) {
			predicates.add(builder.equal(root.get("municipioId"),pessoaFilter.getId()));
		}
		
		if(pessoaFilter.getNumero() != null) {
			predicates.add(builder.like(builder.lower(root.get("numero")),"%" + pessoaFilter.getNome().toLowerCase() + "%"));
		}
		
//		if(pessoaFilter.getSexo() != null) {
//			predicates.add(builder.like(builder.lower(root.get("endereco")),"%" + pessoaFilter.getNome().toLowerCase() + "%"));
//		}
//		
//		if(pessoaFilter.getIdTipoPessoa() != null) {
//			predicates.add(builder.equal(root.get("idTipoPessoa"),pessoaFilter.getIdTipoPessoa()));
//		}
		
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Pessoa> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	
	private long total(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);	
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		Predicate[] predicates = criarRestricoes(pessoaFilter,builder,root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
