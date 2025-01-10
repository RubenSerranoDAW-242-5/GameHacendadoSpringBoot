package com.openwebinars.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.openwebinars.spring.entidades.LineaPedidos;
import com.openwebinars.spring.repositorio.LineaPedidosRepositorio;

@Primary
@Service
public class LineaPedidosServicio {
    @Autowired
    private LineaPedidosRepositorio repositorio;

    public List<LineaPedidos> findAll() {
        return repositorio.findAll();
    }

    public LineaPedidos add(LineaPedidos lp) {
        repositorio.saveAndFlush(lp);
        return lp;
    }

    public LineaPedidos edit(LineaPedidos lp) {
        repositorio.saveAndFlush(lp);
        return lp;
    }

    public void delete(long id) {
        repositorio.deleteById(id);
    }

    public LineaPedidos findById(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public LineaPedidos findByCartaId(long idCarta) {
        return repositorio.findByCartaId(idCarta);
    }

    public List<LineaPedidos> findByPedidoId(long idPedido) {
        return repositorio.findByPedidoId(idPedido);
    }
}
