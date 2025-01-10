package com.openwebinars.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.repositorio.UsuarioRepositorio;

@Primary
@Service
public class UsuarioServicio {

	@Autowired
	private UsuarioRepositorio repositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Usuario> findAll() {
		return repositorio.findAll();
	}

	public Usuario add(Usuario u) {

		u.setContraseña(passwordEncoder.encode(u.getContraseña()));
		u.setRol("user");
		repositorio.saveAndFlush(u);
		return u;

	}

	public Usuario edit(Usuario u) {

		repositorio.saveAndFlush(u);
		return u;

	}

	public Usuario findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Usuario findByEmail(String email) {
		return repositorio.findByEmail(email);
	}

}
