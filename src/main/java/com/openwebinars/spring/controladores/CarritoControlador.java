package com.openwebinars.spring.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.LineaPedidos;
import com.openwebinars.spring.entidades.Pedidos;
import com.openwebinars.spring.entidades.Pedidos.EstadoPedido;
import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.servicios.CartaServicio;
import com.openwebinars.spring.servicios.LineaPedidosServicio;
import com.openwebinars.spring.servicios.PedidosServicio;
import com.openwebinars.spring.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoControlador {

    @Autowired
    private UsuarioServicio bdUsuario;

    @Autowired
    private LineaPedidosServicio bdlineaPedidos;

    @Autowired
    private PedidosServicio bdPedidos;

    @Autowired
    private HttpSession sesion;

    @Autowired
	private CartaServicio bdCarta;

    @GetMapping("/carrito")
    public String carrito(Model model) {

        Long id = (Long) sesion.getAttribute("IdPedido");

        if (id != null) {
            Pedidos p = bdPedidos.findById(id);
            List<LineaPedidos> listaLineaPedidos = bdlineaPedidos.findByPedidoId(id);

            model.addAttribute("pedido", p);
            model.addAttribute("listaLineaPedidos", listaLineaPedidos);
        } else {
            model.addAttribute("pedido", null);
            model.addAttribute("listaLineaPedidos", null);
        }

        return "public/carrito";
    }

    @PostMapping("/carrito/add")
    public String carritoAdd(Model model, @RequestParam(value = "idCarta") Long IdCarta,
            @RequestParam(value = "cantidad") Integer cantidad) {

        Carta carta = bdCarta.findById(IdCarta);
        Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

        Pedidos p;
        if (bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, u.getId()) != null) {
            p = bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, u.getId());
        } else {
            p = new Pedidos(LocalDateTime.now(), 0, u.getDireccion(), EstadoPedido.EN_PROCESO, u);
            bdPedidos.add(p);
        }

        LineaPedidos existegLinea = bdlineaPedidos.findByCartaId(carta.getId());

        if (existegLinea != null) {
            existegLinea.setCantidad(existegLinea.getCantidad() + cantidad);
            existegLinea.setPrecioTotalLinea(existegLinea.getPrecioTotalLinea() + (carta.getPrecioCarta() * cantidad));
            bdlineaPedidos.add(existegLinea);
        } else {
            LineaPedidos linea = new LineaPedidos(cantidad, (carta.getPrecioCarta() * cantidad), p, carta);
            bdlineaPedidos.add(linea);
        }

        p.setPrecioTotal(p.getPrecioTotal() + (carta.getPrecioCarta() * cantidad));
        bdPedidos.edit(p);

        if (p.getId() != null) {
            sesion.setAttribute("IdPedido", p.getId());
        }

        if (bdPedidos.findCarta(IdCarta, u.getId()) != null) {
            Integer contadorCarrito = (Integer) sesion.getAttribute("contador_carrito");

            sesion.setAttribute("contador_carrito", contadorCarrito + 1);
            model.addAttribute("contador_carrito", contadorCarrito + 1);
        }
        return "redirect:/";
    }
}
