package br.gov.prefeitura.msc.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
//	@Autowired
//	UsuarioRepository userRepository;

//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Usuario user = userRepository.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
//
//		return UserDetailsImpl.build(user);
//	}

}
