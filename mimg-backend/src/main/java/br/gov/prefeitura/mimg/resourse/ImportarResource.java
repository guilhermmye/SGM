package br.gov.prefeitura.mimg.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.mimg.service.ImportarService;

@RestController
@RequestMapping("/importar")
public class ImportarResource {
	
	@Autowired
	private ImportarService            importarService;

	@GetMapping("/tudo")
	public void importarTudo(){
		importarService.importarTudo();
	} 
	
	@GetMapping("/regioes")
	public void importarRegioes(){
		importarService.importarRegiao();
	}
	
	@GetMapping("/ufs")
	public void importarUf(){
		importarService.importarUf();
	}
	
	@GetMapping("/mesoregioes")
	public void importarMesoRegiao(){
		importarService.importarMesorregiao();
	}
	
	@GetMapping("/microrregioes")
	public void importarMicroRegiaoResource(){
		importarService.importarMicrorregiao();
	}	
	
	@GetMapping("/regioesIntermediarias")
	public void importarRegiaoIntermediariaResource(){
		importarService.importarRegiaoIntermediaria();
	}
	
	@GetMapping("/regioesImediatas")
	public void importarRegiaoImediataResource(){
		importarService.importarRegiaoImediata();
	}
	
	@GetMapping("/municipios")
	public void importarMunicipioResource(){
		importarService.importarMunicipio();
	}
	
}
