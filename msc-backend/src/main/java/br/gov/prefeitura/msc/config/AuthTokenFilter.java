package br.gov.prefeitura.msc.config;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import br.gov.prefeitura.msc.service.UserDetailsImpl;
import br.gov.prefeitura.msc.service.UserDetailsServiceImpl;
import br.gov.prefeitura.msc.util.JwtUtils;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String username = jwtUtils.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = teste(username,jwt);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Não é possível definir a autenticação do usuário: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
	
	private UserDetails teste(String username,String token){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(token);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username",username);
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> response = restTemplate.exchange("http://localhost:5050/seguranca/api/validarToken/"+username,HttpMethod.GET,new HttpEntity<Object>(headers),Object.class,request);
		
		Gson gson = new Gson();
		String json = gson.toJson(response.getBody()); 
		Usuario usuario = gson.fromJson(json, Usuario.class);  		
		return UserDetailsImpl.build(usuario);
	}
	
	@Getter
	@Setter
	public class Usuario {
		private Integer id;
		private String username;
		private String email;
		private String password;
		private Set<Role> roles = new HashSet<>();

		public Usuario() {
		}

		public Usuario(String username, String email, String password) {
			this.username = username;
			this.email = email;
			this.password = password;
		}
	}
	
	@Getter
	@Setter
	public class Role {
	private Integer id;
	private ERole name;	
	private String descricao;

	public Role() {

	}
	public Role(ERole name) {
		this.name = name;
	}
	}
	
	public enum ERole {
		ROLE_USUARIO,
	    ROLE_TECNICO,
	    ROLE_ADMIN
	}
}
