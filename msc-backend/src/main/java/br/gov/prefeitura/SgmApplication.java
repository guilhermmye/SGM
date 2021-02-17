package br.gov.prefeitura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.prefeitura.mimg.model.Regiao;
import br.gov.prefeitura.mimg.resourse.RegiaoResource;
import br.gov.prefeitura.mimg.service.RegiaoService;

@SpringBootApplication
public class SgmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgmApplication.class, args);
		
	
		System.out.println("teste");	
	}

}
