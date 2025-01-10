package com.openwebinars.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.Pedidos;
import com.openwebinars.spring.entidades.Pedidos.EstadoPedido;
import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.repositorio.PedidosRepository;

@Primary
@Service
public class PedidosServicio {

    @Autowired
    private PedidosRepository repositorio;

    public List<Pedidos> findAll() {
        return repositorio.findAll();
    }

    public Pedidos add(Pedidos p) {
        repositorio.saveAndFlush(p);
        return p;
    }

    public Pedidos edit(Pedidos p) {
        repositorio.saveAndFlush(p);
        return p;
    }

    public void delete(long id) {
        repositorio.deleteById(id);
    }

    public Pedidos findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Pedidos findByUsuarioId(long id) {
        return repositorio.findByUsuarioId(id);
    }

    public Pedidos findByEstado(EstadoPedido estado, Long id) {
        return repositorio.findByEstado(estado, id);
    }

    public Carta findCarta(Long c, Long u) {
        return repositorio.findCarta(c, u);
    }

    public Integer countPedidos(Usuario u, Pedidos p) {
        return repositorio.countPedidos(u, p);
    }
}
