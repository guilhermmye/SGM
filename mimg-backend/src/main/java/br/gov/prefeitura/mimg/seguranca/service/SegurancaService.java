package br.gov.prefeitura.mimg.seguranca.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.gov.prefeitura.mimg.seguranca.filter.RetornoValidarTokenDTO;

@Service
public class SegurancaService {
	
	public RetornoValidarTokenDTO validarToken(String token){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-wso2-auth-header","Bearer "+token);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("token",token);
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.exchange("https://localhost:8243/seguranca/v1/validarToken/"+token,HttpMethod.GET,new HttpEntity<Object>(headers),Object.class,request);
		
		Gson gson = new Gson();
		String json = gson.toJson(response.getBody()); 
		RetornoValidarTokenDTO validarToken = gson.fromJson(json, RetornoValidarTokenDTO.class); 

		return validarToken;
	}
	
	
	

	
	

}
