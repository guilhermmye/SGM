package br.gov.prefeitura.mimg.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.UfRepository;
import br.gov.prefeitura.mimg.repository.uf.filter.UfFilter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ufs")
public class UfResource {
	
	@Autowired
	private UfRepository  	ufRepository;

	@RequestMapping( value ="/listar", method = RequestMethod.GET)
	public List<Uf> listar(){
		return ufRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Uf> pesquisar(UfFilter ufFilter,Pageable pageable){
		return ufRepository.filtrar(ufFilter,pageable);
	}

}
