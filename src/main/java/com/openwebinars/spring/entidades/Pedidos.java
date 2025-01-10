package com.openwebinars.spring.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pedidos")
public class Pedidos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDateTime fecha;

	@Column(precision = 10, scale = 2)
	private Integer precioTotal;

	@Column(length = 800)
	private String direccionEnvio;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('EN_PROCESO','TERMINADO')", nullable = false)
	private EstadoPedido estado;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

	public Pedidos() {
	}

	// Constructor
	public Pedidos(LocalDateTime fecha, Integer precioTotal, String direccionEnvio, EstadoPedido estado, Usuario usuario) {
		this.fecha = fecha;
		this.precioTotal = precioTotal;
		this.direccionEnvio = direccionEnvio;
		this.estado = estado;
		this.usuario = usuario;
	}

	public enum EstadoPedido {
		EN_PROCESO, TERMINADO 
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Integer getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Integer precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
