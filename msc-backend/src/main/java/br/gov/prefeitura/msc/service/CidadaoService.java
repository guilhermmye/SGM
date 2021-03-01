package br.gov.prefeitura.msc.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.msc.model.Cidadao;
import br.gov.prefeitura.msc.model.TipoPessoa;
import br.gov.prefeitura.msc.repository.CidadaoRepository;

@Service
public class CidadaoService {
	
	@Autowired
	private CidadaoRepository      cidadaoRepository;
	
	@Autowired
	private TipoPessoaService		tipoPessoaService;
	
	private final Integer CPF  = 1;
	private final Integer CNPJ = 2;
	
	public Cidadao atualizar(Integer id,Cidadao cidadao) {		
		Cidadao cidadaoSalvo = buscarPessoaPorId(id);		
		BeanUtils.copyProperties(cidadao,cidadaoSalvo,"id");		
		return cidadaoRepository.save(cidadaoSalvo);		
	}
	
	public Cidadao cadastrar(Cidadao cidadao) {		
		TipoPessoa tipoPessoa = tipoPessoaService.buscarTipoPessoaPorId(cidadao.getCpfCnpj().length() == 11 ? CPF : CNPJ);
		cidadao.setTipoPessoa(tipoPessoa);
		cidadao.setMunicipioId(1);
		return cidadaoRepository.save(cidadao);		
	}
	
	private Cidadao buscarPessoaPorId(Integer id) {
		Optional<Cidadao> cidadaoSalvo = cidadaoRepository.findById(id);		
		if(cidadaoSalvo == null || cidadaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cidadaoSalvo.get();
	}

}
