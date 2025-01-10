package com.openwebinars.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openwebinars.spring.entidades.LineaPedidos;

import java.util.List;

public interface LineaPedidosRepositorio extends JpaRepository<LineaPedidos, Long> {

    @Query("SELECT lp FROM LineaPedidos lp WHERE lp.carta.id = ?1")
    public LineaPedidos findByCartaId(Long idCarta);

    @Query("SELECT lp FROM LineaPedidos lp WHERE lp.pedido.id = ?1")
    public List<LineaPedidos> findByPedidoId(Long idPedido);
}
