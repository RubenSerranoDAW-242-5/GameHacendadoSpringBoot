package com.openwebinars.spring.repositorio;

import com.openwebinars.spring.entidades.Categorias;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categorias, Long> {
    
    public Categorias findByCategoria(String categoria);
}
