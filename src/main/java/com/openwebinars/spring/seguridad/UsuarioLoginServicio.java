package com.openwebinars.spring.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.repositorio.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioLoginServicio implements UserDetailsService {

	@Autowired
	UsuarioRepositorio repositorio;

	@Autowired
    private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repositorio.findByEmail(username);
		
		System.out.println(usuario);

		UserBuilder builder = null;

		if (usuario != null) {
			builder = User.withUsername(username);
			
			builder.disabled(false);
			builder.password(usuario.getContraseña());
			builder.roles(usuario.getRol());
			builder.authorities(new SimpleGrantedAuthority("ROLE_"+usuario.getRol()));

			session.setAttribute("usuarioLogueado", true);
            session.setAttribute("usuarioAdmin", usuario.getRol().equals("admin"));
			session.setAttribute("id", usuario.getId());
			
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return builder.build();
	}

}
