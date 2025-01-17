package com.openwebinars.spring.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carta")
public class Carta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nombreCarta;

	@Column(nullable = false)
	private String tipoCarta;

	@Column
	private String costeCarta;

	@Column(nullable = false)
	private String color;

	@Column(nullable = false, unique = true)
	private String codigoCarta;

	@Column(precision = 10, scale = 2, nullable = false)
	private Integer precioCarta;

	@Column(length = 750)
	private String img;

	@Column(nullable = false)
	private Integer cantidad;

	@ManyToMany(mappedBy = "cartas")
	private Set<Categorias> categorias = new HashSet<>();

	public Carta() {
	}

	public Carta(String nombreCarta, String tipoCarta, String costeCarta, String color, String codigoCarta,
			Integer precioCarta, String img, Integer cantidad) {
		this.nombreCarta = nombreCarta;
		this.tipoCarta = tipoCarta;
		this.costeCarta = costeCarta;
		this.color = color;
		this.codigoCarta = codigoCarta;
		this.precioCarta = precioCarta;
		this.img = img;
		this.cantidad = cantidad;
	}

	public Carta(String nombreCarta, String tipoCarta, String costeCarta, String color, String codigoCarta,
	Integer precioCarta, String img, Integer cantidad, Set<Categorias> categorias) {
		this.nombreCarta = nombreCarta;
		this.tipoCarta = tipoCarta;
		this.costeCarta = costeCarta;
		this.color = color;
		this.codigoCarta = codigoCarta;
		this.precioCarta = precioCarta;
		this.img = img;
		this.cantidad = cantidad;
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCarta() {
		return nombreCarta;
	}

	public void setNombreCarta(String nombreCarta) {
		this.nombreCarta = nombreCarta;
	}

	public String getTipoCarta() {
		return tipoCarta;
	}

	public void setTipoCarta(String tipoCarta) {
		this.tipoCarta = tipoCarta;
	}

	public String getCosteCarta() {
		return costeCarta;
	}

	public void setCosteCarta(String costeCarta) {
		this.costeCarta = costeCarta;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCodigoCarta() {
		return codigoCarta;
	}

	public void setCodigoCarta(String codigoCarta) {
		this.codigoCarta = codigoCarta;
	}

	public Integer getPrecioCarta() {
		return precioCarta;
	}

	public void setPrecioCarta(Integer precioCarta) {
		this.precioCarta = precioCarta;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Set<Categorias> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categorias> categorias) {
		this.categorias = categorias;
	}
}
