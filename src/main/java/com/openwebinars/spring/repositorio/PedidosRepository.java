package com.openwebinars.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.Pedidos;
import com.openwebinars.spring.entidades.Pedidos.EstadoPedido;
import com.openwebinars.spring.entidades.Usuario;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

    public Pedidos findByUsuarioId(Long id);

    @Query("SELECT p FROM Pedidos p WHERE p.estado = ?1 AND p.usuario.id = ?2")
    public Pedidos findByEstado(EstadoPedido estado, Long id);

    @Query("SELECT c FROM Pedidos p JOIN LineaPedidos lp ON p.id = lp.pedido.id JOIN Carta c ON lp.carta.id = c.id WHERE p.usuario.id = ?2 AND lp.carta.id = ?1")
    public Carta findCarta(Long cartaId, Long usuarioId);

    @Query("SELECT COUNT(*) FROM Pedidos p JOIN LineaPedidos lp ON p.id = lp.pedido.id WHERE p.usuario = ?1 AND lp.pedido = ?2")
    public Integer countPedidos(Usuario u, Pedidos p);
}
