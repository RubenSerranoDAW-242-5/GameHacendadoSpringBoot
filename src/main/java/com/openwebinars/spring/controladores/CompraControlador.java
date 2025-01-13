package com.openwebinars.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openwebinars.spring.entidades.Pedidos;
import com.openwebinars.spring.entidades.Pedidos.EstadoPedido;
import com.openwebinars.spring.servicios.LineaPedidosServicio;
import com.openwebinars.spring.servicios.PedidosServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class CompraControlador {

    @Autowired
    private LineaPedidosServicio bdlineaPedidos;

    @Autowired
    private PedidosServicio bdPedidos;

    @Autowired
    private HttpSession sesion;

    @GetMapping("/compra")
    public String compra(@RequestParam("idPedido") Long idPedido, Model model) {
        Pedidos pedido = bdPedidos.findById(idPedido);
        if (pedido == null) {
            return "redirect:/";
        }

        if (sesion.getAttribute("gastoEnvio") == null) {
            sesion.setAttribute("gastoEnvio", (int) (Math.random() * 5) + 1);
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("listaLineaPedidos", bdlineaPedidos.findByPedidoId(idPedido));
        model.addAttribute("gastoEnvio", sesion.getAttribute("gastoEnvio"));

        return "layout/compraCarrito";
    }

    @GetMapping("/finalizarCompra")
    public String finalizarCompra(@RequestParam("idPedido") Long idPedido) {
        Pedidos pedido = bdPedidos.findById(idPedido);
        if (pedido != null) {
            pedido.setEstado(EstadoPedido.TERMINADO);
            bdPedidos.edit(pedido);
        }
        return "redirect:/";
    }
}
