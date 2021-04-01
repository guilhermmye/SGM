package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.service.ImportarService;

@RestController
@RequestMapping("/importar")
public class ImportarResource {
	
	@Autowired
	private ImportarService            importarService;

	@PostMapping("/tudo")
	public void importarTudo(){
		importarService.importarTudo();
	} 
	
	@PostMapping("/regioes")
	public void importarRegioes(){
		importarService.importarRegiao();
	}
	
	@PostMapping("/ufs")
	public void importarUf(){
		importarService.importarUf();
	}
	
	@PostMapping("/mesoregioes")
	public void importarMesoRegiao(){
		importarService.importarMesorregiao();
	}
	
	@PostMapping("/microrregioes")
	public void importarMicroRegiaoResource(){
		importarService.importarMicrorregiao();
	}	
	
	@PostMapping("/regioesIntermediarias")
	public void importarRegiaoIntermediariaResource(){
		importarService.importarRegiaoIntermediaria();
	}
	
	@PostMapping("/regioesImediatas")
	public void importarRegiaoImediataResource(){
		importarService.importarRegiaoImediata();
	}
	
	@PostMapping("/municipios")
	public void importarMunicipioResource(){
		importarService.importarMunicipio();
	}
	
}
