package com.openwebinars.spring.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LineaPedidos")
public class LineaPedidos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer cantidad;

	@Column(nullable = false)
	private Integer precioTotalLinea;

	@ManyToOne
	@JoinColumn(name = "idPedido", nullable = false)
	private Pedidos pedido;

	@ManyToOne
	@JoinColumn(name = "idCarta", nullable = false)
	private Carta carta;

	public LineaPedidos(Integer cantidad, Integer precioTotalLinea, Pedidos pedido, Carta carta) {
		this.cantidad = cantidad;
		this.precioTotalLinea = precioTotalLinea;
		this.pedido = pedido;
		this.carta = carta;
	}

	public LineaPedidos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getPrecioTotalLinea() {
		return precioTotalLinea;
	}

	public void setPrecioTotalLinea(Integer precioTotalLinea) {
		this.precioTotalLinea = precioTotalLinea;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}
}
