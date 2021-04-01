package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.model.Municipio;
import br.gov.prefeitura.mimg.repository.MunicipioRepository;
import br.gov.prefeitura.mimg.repository.municipio.filter.MunicipioFilter;

@RestController
@RequestMapping("/municipios")
public class MunicipioResource {
	
	@Autowired
	private MunicipioRepository  	municipioRepository;

	@GetMapping
	public Page<Municipio> pesquisar(MunicipioFilter municipioFilter,Pageable pageable){
		return municipioRepository.filtrar(municipioFilter,pageable);
	}	
}
