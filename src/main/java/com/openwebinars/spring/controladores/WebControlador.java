package com.openwebinars.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.Categorias;
import com.openwebinars.spring.entidades.Pedidos;
import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.entidades.Pedidos.EstadoPedido;
import com.openwebinars.spring.servicios.CartaServicio;
import com.openwebinars.spring.servicios.CategoriaServicio;
import com.openwebinars.spring.servicios.PedidosServicio;
import com.openwebinars.spring.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebControlador {

    @Autowired
    private CartaServicio bdCarta;

    @Autowired
    private CategoriaServicio bdCategoria;

    @Autowired
    private UsuarioServicio bdUsuario;

    @Autowired
    private PedidosServicio bdPedidos;

    @Autowired
    private HttpSession sesion;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {

        List<Carta> cartas;
        List<Categorias> categorias = bdCategoria.findAll();

        if (model.getAttribute("cartas") == null || model.getAttribute("categorias") == null) {
            cartas = bdCarta.findAll();
            model.addAttribute("cartas", cartas);
            model.addAttribute("categorias", categorias);
        }

        
        model.addAttribute("currentUri", request.getRequestURI());

        model.addAttribute("usuarioLogueado",
                (sesion.getAttribute("usuarioLogueado") != null) ? sesion.getAttribute("usuarioLogueado") : false);

        model.addAttribute("usuarioAdmin",
                (sesion.getAttribute("usuarioAdmin") != null) ? sesion.getAttribute("usuarioAdmin") : false);
        boolean log = (boolean) model.getAttribute("usuarioLogueado");

        if (log) {
            Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

            Pedidos p = bdPedidos.findByEstado(EstadoPedido.EN_PROCESO, u);

            if (p != null) {

                Integer contadorPedidos = bdPedidos.countPedidos(u, p);

                model.addAttribute("contador_carrito", contadorPedidos);
                sesion.setAttribute("contador_carrito", contadorPedidos);
                sesion.setAttribute("IdPedido", p.getId());

            } else {
                model.addAttribute("contador_carrito", 0);
                sesion.setAttribute("contador_carrito", 0);
            }

        }
        return "public/index";
    }

    @GetMapping("/buscar")
    public String buscarCartas(@RequestParam("query") String query, @RequestParam("categoria") String categoria, Model model) {

        List<Carta> resultados;
        //ver filtro categorias
        if (categoria == null || categoria.isEmpty()) {
            resultados = bdCarta.findByAll(query);
        } else {
            resultados = bdCarta.findByAllAndCategoria(query, categoria);
        }
        if (resultados.isEmpty()) {
            model.addAttribute("mensaje", "No se encontraron cartas que coincidan con la búsqueda.");
        }
        model.addAttribute("cartas", resultados);


        model.addAttribute("usuarioLogueado",
                (sesion.getAttribute("usuarioLogueado") != null) ? sesion.getAttribute("usuarioLogueado") : false);

        return "layout/resultado";
    }
}
