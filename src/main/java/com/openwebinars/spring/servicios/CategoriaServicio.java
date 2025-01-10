package com.openwebinars.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.entidades.Categorias;
import com.openwebinars.spring.repositorio.CategoriaRepositorio;

@Primary
@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio repositorio;

    public List<Categorias> findAll() {
        return repositorio.findAll();
    }

    public Categorias add(Categorias c) {
        repositorio.saveAndFlush(c);
        return c;
    }

    public void delete(long id) {
        repositorio.deleteById(id);
    }

    public Categorias findByCategoria(String categoria) {
        return repositorio.findByCategoria(categoria);
    }

    public Categorias findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Categorias edit(Categorias c) {
        repositorio.saveAndFlush(c);
        return c;
    }
}
