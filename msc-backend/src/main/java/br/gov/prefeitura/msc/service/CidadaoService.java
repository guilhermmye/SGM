package br.gov.prefeitura.msc.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	private final static Integer CPF  = 1;
	private final static Integer CNPJ = 2;
	
	@Transactional
	public Cidadao atualizar(Integer id,Cidadao cidadao) {		
		Cidadao cidadaoSalvo = buscarPessoaPorId(id);
		TipoPessoa tipoPessoa = tipoPessoaService.buscarTipoPessoaPorId(cidadao.getCpfCnpj().length() == 11 ? CPF : CNPJ);
		cidadao.setTipoPessoa(tipoPessoa);
		BeanUtils.copyProperties(cidadao,cidadaoSalvo,"id");		
		return cidadaoRepository.save(cidadaoSalvo);		
	}
	
	@Transactional
	public Cidadao cadastrar(Cidadao cidadao) {	
		validarPessoa(cidadao.getCpfCnpj());
		TipoPessoa tipoPessoa = tipoPessoaService.buscarTipoPessoaPorId(cidadao.getCpfCnpj().length() == 11 ? CPF : CNPJ);
		cidadao.setTipoPessoa(tipoPessoa);
		return cidadaoRepository.save(cidadao);		
	}
	
	private Cidadao buscarPessoaPorId(Integer id) {
		Optional<Cidadao> cidadaoSalvo = cidadaoRepository.findById(id);		
		if(cidadaoSalvo == null || cidadaoSalvo.get() == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cidadaoSalvo.get();
	}
	
	public Cidadao buscarPessoaPorCpfCnpj(String cpfCnpj) {
		Cidadao cidadaoSalvo = cidadaoRepository.obterPorCpfCnpj(cpfCnpj);		
		if(cidadaoSalvo == null ) {
			throw new EmptyResultDataAccessException(1);
		}
		return cidadaoSalvo;
	}
	
	private void validarPessoa(String cpfCnpj) {
		Cidadao cidadao = cidadaoRepository.obterPorCpfCnpj(cpfCnpj);	
		
		if(cidadao != null && cidadao.getId() != null) {
			throw new DataIntegrityViolationException("Cidadão já Cadastrado.");
		}
	}
	
	

}
