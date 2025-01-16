package com.openwebinars.spring.servicios;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.repositorio.CartaRepositorio;

@Primary
@Service
public class CartaServicio {

	@Autowired
	private CartaRepositorio repositorio;

	public List<Carta> findAll() {
		return repositorio.findAll();
	}

	public Carta add(Carta c) {
		repositorio.saveAndFlush(c);
		return c;
	}

	public Carta edit(Carta c) {
		repositorio.saveAndFlush(c);
		return c;
	}

	public void delete(long id) {
		repositorio.deleteById(id);
	}

	public Carta findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Carta findByNombreCarta(String nombreCarta) {
		return repositorio.findByNombreCarta(nombreCarta);
	}

	public Carta findByCodigoCarta(String codigoCarta) {
		return repositorio.findByCodigoCarta(codigoCarta);
	}

	public Carta findByTipoCarta(String tipoCarta) {
		return repositorio.findByTipoCarta(tipoCarta);
	}

	public Carta findByColor(String color) {
		return repositorio.findByColor(color);
	}

	public Carta findByCosteCarta(String costeCarta) {
		return repositorio.findByCosteCarta(costeCarta);
	}

	public Carta findByPrecioCarta(BigDecimal precioCarta) {
		return repositorio.findByPrecioCarta(precioCarta);
	}

	public List<Carta> findByAll(String query) {
		return repositorio.findByAll(query);
	}
	public List<Carta> findByAllAndCategoria(String query, String categoria) {
		return repositorio.findByAllAndCategoria(query, categoria);
	}
}
