package br.gov.prefeitura.mimg.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Mesorregiao;
import br.gov.prefeitura.mimg.model.Microrregiao;
import br.gov.prefeitura.mimg.model.Municipio;
import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.model.RegiaoImediata;
import br.gov.prefeitura.mimg.model.RegiaoIntermediaria;
import br.gov.prefeitura.mimg.model.Uf;

@Service
public class ImportarService {
	
	@Autowired
	private UfService  					ufService;
	
	@Autowired
	private RegiaoService 				regiaoService;
	
	@Autowired
	private RegiaoIntermediariaService  regiaoIntermediariaService;
	
	@Autowired
	private RegiaoImediataService  		regiaoImediataService;
	
	@Autowired
	private MunicipioService  			municipioService;
	
	@Autowired
	private MicrorregiaoService         microrregiaoService;	
	
	@Autowired
	private MesorregiaoService          mesorregiaoService;
	
	private final static String URL = "localhost:8243/mimg_ibge/v1/localidades";
	
	private final static String PARAMETROS_UF = "11|12|13|14|15|16|17|21|22|23|24|25|26|27|28|29|31|32|33|35|41|42|43|50|51|52|53";
	private final static String PARAMETROS_REGIAO = "1|2|3|4|5";
	private final static String PARAMETROS_REGIAO_INTERMEDIARIA = "31/regioes-intermediarias";
	private final static String PARAMETROS_REGIAO_IMEDIATA = "31/regioes-imediatas";
	private final static String PARAMETROS_MUNICIPIO = "31/municipios";
	private final static String PARAMETROS_MICRORREGIAO = "3101|3102|3103|3104|3105|3106|3107|3108|3109|3110|3111|3112/microrregioes";
	private final static String PARAMETROS_MESORREGIAO = "31/mesorregioes";
	
	@Transactional
	public void importarTudo() {
		
		importarRegiao();
		importarUf();
		importarMesorregiao();
		importarMicrorregiao();
	    importarRegiaoIntermediaria();
		importarRegiaoImediata();
		importarMunicipio();
		
	}
	
	@Transactional
	public void importarRegiao(){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(URL)
				.path("regioes")
				.queryParam(PARAMETROS_REGIAO)
				.build();
		
		ResponseEntity<Regiao[]> regiao = restTemplate.getForEntity(uri.toUriString(), Regiao[].class);
		
		List<Regiao> regioes = Arrays.asList(regiao.getBody());

		regiaoService.salvarRegioes(regioes);	
	}
	
	@Transactional
	public void importarUf(){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(URL)
				.path("estados")
				.queryParam(PARAMETROS_UF)
				.build();
		String caminho = uri.toString().replace("?","/");
		ResponseEntity<Uf[]> uf = restTemplate.getForEntity(caminho, Uf[].class);
		
		List<Uf> ufs = Arrays.asList(uf.getBody());
			
		ufService.salvarUfs(ufs);		
	}
	
	@Transactional
	public void importarMesorregiao(){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(URL)
				.path("estados")
				.queryParam(PARAMETROS_MESORREGIAO)
				.build();
		
		String caminho = uri.toUriString().replace("?","/");
		ResponseEntity<Mesorregiao[]> mesorregiao = restTemplate.getForEntity(caminho, Mesorregiao[].class);
		
		List<Mesorregiao> mesorregiaos = Arrays.asList(mesorregiao.getBody());
		
		for (Mesorregiao mesorregiao2 : mesorregiaos) {
			if(mesorregiao2 != null && mesorregiao2.getUf() == null)
			{
				Uf uf = ufService.buscarUfPorId(31);
				mesorregiao2.setUf(uf);
			}
		}
	
		mesorregiaoService.salvarMesorregioes(mesorregiaos);
			
	} 
	
	@Transactional
	public void importarMicrorregiao(){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(URL)
				.path("mesorregioes")
				.queryParam(PARAMETROS_MICRORREGIAO)
				.build();
		
		String NovoCaminho   = uri.toUriString().replace('-', '/');
		String caminho = NovoCaminho.replace("?","/");		
		
		ResponseEntity<Microrregiao[]> microrregiao = restTemplate.getForEntity(caminho, Microrregiao[].class);
		
		List<Microrregiao> microrregioes = Arrays.asList(microrregiao.getBody());
		
		microrregiaoService.salvarMicrorregioes(microrregioes);			
	}
	
	@Transactional
	public void importarRegiaoIntermediaria() {

		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host(URL).path("estados").queryParam(PARAMETROS_REGIAO_INTERMEDIARIA).build();
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
	
	@Transactional
	public void importarRegiaoImediata() {

		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host(URL).path("estados").queryParam(PARAMETROS_REGIAO_IMEDIATA).build();
		String caminho = uri.toString().replace("?", "/");
		ResponseEntity<RegiaoImediata[]> regiaoImediata = restTemplate.getForEntity(caminho, RegiaoImediata[].class);
		
		List<RegiaoImediata> regiaoImediatas = Arrays.asList(regiaoImediata.getBody());

		regiaoImediataService.salvarRegiaoImediatas(regiaoImediatas);		
	}
	
	@Transactional
	public void importarMunicipio(){
		
		RestTemplate restTemplate = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(URL)
				.path("estados")
				.queryParam(PARAMETROS_MUNICIPIO)
				.build();
			
		String caminho = uri.toString().replace("?","/");				
		ResponseEntity<Municipio[]> municipio = restTemplate.getForEntity(caminho, Municipio[].class);	
		List<Municipio> municipios = Arrays.asList(municipio.getBody());		
		municipioService.salvarMunicipio(municipios);	
	} 
	
}
