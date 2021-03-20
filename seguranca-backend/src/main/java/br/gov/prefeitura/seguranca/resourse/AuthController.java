package br.gov.prefeitura.seguranca.resourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.prefeitura.seguranca.model.ERole;
import br.gov.prefeitura.seguranca.model.Role;
import br.gov.prefeitura.seguranca.model.Usuario;
import br.gov.prefeitura.seguranca.payload.request.LoginRequest;
import br.gov.prefeitura.seguranca.payload.request.SignupRequest;
import br.gov.prefeitura.seguranca.payload.response.JwtResponse;
import br.gov.prefeitura.seguranca.payload.response.MessageResponse;
import br.gov.prefeitura.seguranca.repository.role.RoleRepository;
import br.gov.prefeitura.seguranca.repository.usuario.UsuarioRepository;
import br.gov.prefeitura.seguranca.service.UserDetailsImpl;
import br.gov.prefeitura.seguranca.util.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("O nome de usuário já existe!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("O e-mail já está em uso!"));
		}

		// Criação de nova conta
		Usuario user = new Usuario(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		List<String> list = new ArrayList<String>(strRoles);
		Set<Role> roles = new HashSet<>();

				switch (list.get(0)) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Role não encontrada."));
					roles.add(adminRole);

					break;
				case "ROLE_TECNICO":
					Role modRole = roleRepository.findByName(ERole.ROLE_TECNICO)
							.orElseThrow(() -> new RuntimeException("Role não encontrada."));
					roles.add(modRole);

					break;
				case "ROLE_USUARIO":
					Role userRole = roleRepository.findByName(ERole.ROLE_USUARIO)
							.orElseThrow(() -> new RuntimeException("Role não encontrada."));
					roles.add(userRole);
					break;
//				default:
//					Role userRol = roleRepository.findByName(ERole.ROLE_USUARIO)
//							.orElseThrow(() -> new RuntimeException("Role não encontrada."));
//					roles.add(userRol);
				}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Usuário registrado com sucesso!"));
	}
}
