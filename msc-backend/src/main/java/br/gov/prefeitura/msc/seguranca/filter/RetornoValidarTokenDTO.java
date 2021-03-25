package br.gov.prefeitura.msc.seguranca.filter;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoValidarTokenDTO {
	
	private Boolean valido;   
	private Usuario usuario; 
	
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

