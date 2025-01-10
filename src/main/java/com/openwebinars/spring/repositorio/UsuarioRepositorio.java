package com.openwebinars.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openwebinars.spring.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	public Usuario findByEmail(String email);
	
}
