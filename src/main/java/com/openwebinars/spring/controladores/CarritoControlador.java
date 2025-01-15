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

    @PostMapping("/carrito/empty")
    public String CarritoVacio(@RequestParam(value = "idPedido") Long idPedido) {

        List<LineaPedidos> listaLineaPedidos = bdlineaPedidos.findByPedidoId(idPedido);

        for (LineaPedidos lineaPedidos : listaLineaPedidos) {
            Carta carta = lineaPedidos.getCarta();
            carta.setCantidad(carta.getCantidad() + lineaPedidos.getCantidad());
            bdCarta.edit(carta);
            bdlineaPedidos.delete(lineaPedidos.getId());
        }
        Pedidos p = bdPedidos.findById(idPedido);
        p.setPrecioTotal(0);
        bdPedidos.edit(p);
        sesion.setAttribute("contador_carrito", 0);

        return "redirect:/";
    }

    @GetMapping("/carrito/eliminar")
    public String EliminarCarta(@RequestParam(value = "idCarta") Long idCarta,
            @RequestParam(value = "cantidadSeleccionada") Integer cantidad,
            @RequestParam(value = "idPedido") Long idPedido, Model model) {

        List<LineaPedidos> listaLineaPedidos = bdlineaPedidos.findByPedidoId(idPedido);
        Pedidos p = bdPedidos.findById(idPedido);

        for (LineaPedidos lineaPedidos : listaLineaPedidos) {
            if (lineaPedidos.getCarta().getId() == idCarta) {
                Carta carta = lineaPedidos.getCarta();
                if (lineaPedidos.getCantidad() > cantidad) {
                    lineaPedidos.setCantidad(lineaPedidos.getCantidad() - cantidad);
                    lineaPedidos.setPrecioTotalLinea(
                            lineaPedidos.getPrecioTotalLinea() - (lineaPedidos.getCarta().getPrecioCarta() * cantidad));
                    bdlineaPedidos.edit(lineaPedidos);
                } else {
                    bdlineaPedidos.delete(lineaPedidos.getId());
                }
                carta.setCantidad(carta.getCantidad() + cantidad);
                bdCarta.edit(carta);
                p.setPrecioTotal(p.getPrecioTotal() - (lineaPedidos.getCarta().getPrecioCarta() * cantidad));
                bdPedidos.edit(p);
            }
        }

        return "redirect:/carrito";
    }

    @PostMapping("/carrito/add")
    public String carritoAdd(Model model, @RequestParam(value = "idCarta") Long IdCarta,
            @RequestParam(value = "cantidad") Integer cantidad) {

        Carta carta = bdCarta.findById(IdCarta);
        Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

        Pedidos p = bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, u.getId());
        if (p == null) {
            p = new Pedidos(LocalDateTime.now(), 0, u.getDireccion(), EstadoPedido.EN_PROCESO, u);
            bdPedidos.add(p);
        }

        LineaPedidos existeLinea = bdlineaPedidos.findByCartaIdAndPedidoId(carta.getId(), p.getId());

        if (existeLinea != null) {
            existeLinea.setCantidad(existeLinea.getCantidad() + cantidad);
            existeLinea.setPrecioTotalLinea(existeLinea.getPrecioTotalLinea() + (carta.getPrecioCarta() * cantidad));
            bdlineaPedidos.edit(existeLinea);
        } else {
            LineaPedidos linea = new LineaPedidos(cantidad, (carta.getPrecioCarta() * cantidad), p, carta);
            bdlineaPedidos.add(linea);
        }

        p.setPrecioTotal(p.getPrecioTotal() + (carta.getPrecioCarta() * cantidad));
        carta.setCantidad(carta.getCantidad() - cantidad);
        bdCarta.edit(carta);
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
