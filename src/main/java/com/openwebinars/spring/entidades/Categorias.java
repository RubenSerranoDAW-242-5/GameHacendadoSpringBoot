package com.openwebinars.spring.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categorias")
public class Categorias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String categoria;

	@ManyToMany
	@JoinTable(
		name = "categoria_carta",
		joinColumns = @JoinColumn(name = "categoria_id"),
		inverseJoinColumns = @JoinColumn(name = "carta_id")
	)
	private Set<Carta> cartas = new HashSet<>();


	public Categorias() {
	}

	public Categorias(String categoria, Set<Carta> cartas) {
		this.categoria = categoria;
		this.cartas = cartas;
	}

	public void addCarta(Carta carta) {
		this.cartas.add(carta);
		carta.getCategorias().add(this);
	}
	public void removeCarta(Carta carta) {
		this.cartas.remove(carta);
		carta.getCategorias().remove(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Set<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(Set<Carta> cartas) {
		this.cartas = cartas;
	}
}
