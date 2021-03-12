package br.gov.prefeitura.stur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.prefeitura.stur.repository.SturRepository;

@Service
public class SturService {
	
	@Autowired
	private SturRepository      cidadaoRepository;



}
