package br.gov.prefeitura.stur.repository.cidadao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.prefeitura.stur.model.Stur;

public class SturRepositoryImpl implements SturRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	
	@Override
	public List<Stur> filtrarPorCpfCnpj(String cpfCnpj) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Stur> criteria = builder.createQuery(Stur.class);		
		Root<Stur> root = criteria.from(Stur.class);
		
		Predicate[] predicates = criarRestricoes(cpfCnpj,builder,root);
		criteria.where(predicates);
				
		TypedQuery<Stur> query = manager.createQuery(criteria);		
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(String cpfCnpj, CriteriaBuilder builder, Root<Stur> root) {
		List<Predicate> predicates = new ArrayList<>();
			
		if(cpfCnpj!= null) {
			predicates.add(builder.equal(root.get("cpfCnpj"),cpfCnpj.replace(".","").replace("-","").replace("/","")));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	
	


}
