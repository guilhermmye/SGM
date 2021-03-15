package br.gov.prefeitura.mimg.resourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.model.Uf;
import br.gov.prefeitura.mimg.repository.RegiaoIntermediariaRepository;
import br.gov.prefeitura.mimg.repository.regiao.filter.RegiaoFilter;
import br.gov.prefeitura.mimg.repository.regiaoIntermediaria.filter.RegiaoIntermediariaFilter;
import br.gov.prefeitura.mimg.service.RegiaoIntermediariaService;
import br.gov.prefeitura.mimg.service.UfService;

@RestController
@RequestMapping("/regioesIntermediarias")
public class RegiaoIntermediariaResource {
	
	@Autowired
	private RegiaoIntermediariaRepository  regiaoIntermediariaRepository;
	@Autowired
	private RegiaoIntermediariaService  regiaoIntermediariaService;
	@Autowired
	private UfService           ufService;
	
	
	final String PARAMETROS = "31/regioes-intermediarias";
	
	
/*	@GetMapping
	public List<RegiaoIntermediaria> listar(){
		return regiaoIntermediariaRepository.findAll();
	}*/
	

	@GetMapping
	public Page<RegiaoIntermediaria> pesquisar(RegiaoIntermediariaFilter regiaoIntermediariaFilter,Pageable pageable){
		return regiaoIntermediariaRepository.filtrar(regiaoIntermediariaFilter, pageable);
	}
	
	public void pesquisarRegiaoIntermediariaIbge() {

		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host("servicodados.ibge.gov.br/api/v1/localidades").path("estados").queryParam(PARAMETROS).build();
		String caminho = uri.toString().replace("?", "/");
		ResponseEntity<RegiaoIntermediaria[]> regiaoIntermediaria = restTemplate.getForEntity(caminho, RegiaoIntermediaria[].class);

		List<RegiaoIntermediaria> regiaoIntermediarias = Arrays.asList(regiaoIntermediaria.getBody());
		List<RegiaoIntermediaria> novaList = new ArrayList<RegiaoIntermediaria>();
		
		for (RegiaoIntermediaria regiaoIntermediaria2 : regiaoIntermediarias) {
		
				if(regiaoIntermediaria2 != null && regiaoIntermediaria2.getUf() == null)
				{
					RegiaoIntermediaria nova = new RegiaoIntermediaria();
					Uf uf = ufService.buscarUfPorId(31);
					nova.setUf(uf);
					nova.setId(regiaoIntermediaria2.getId());
					nova.setNome(regiaoIntermediaria2.getNome());	
					novaList.add(nova);
				}
			
		}

		regiaoIntermediariaService.salvarRegiaoIntermediarias(novaList);
		
	}
}
