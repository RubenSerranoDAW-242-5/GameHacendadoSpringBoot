package com.openwebinars.spring.controladores;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openwebinars.spring.entidades.Carta;
import com.openwebinars.spring.entidades.Categorias;
import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.servicios.CartaServicio;
import com.openwebinars.spring.servicios.CategoriaServicio;
import com.openwebinars.spring.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
public class ZonaAdminControlador {

    @Autowired
    private UsuarioServicio bdUsuario;

    @Autowired
    private CartaServicio bdCartas;

    @Autowired
    private CategoriaServicio bdCategoria;

    @Autowired
    private HttpSession sesion;

    @GetMapping("/zonaAdmin")
    public String zonaAdmin(Model model) {
        model.addAttribute("currentUri", "/zonaAdmin");
        model.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        model.addAttribute("usuarioAdmin", sesion.getAttribute("usuarioAdmin"));
        return "admin/zonaAdmin";
    }

    @GetMapping("/zonaUsuarios")
    public String zonaUsuarios(@RequestParam(value = "edit", required = false) Long id, Model model) {
        model.addAttribute("currentUri", "/zonaUsuarios");
        model.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        model.addAttribute("usuarioAdmin", sesion.getAttribute("usuarioAdmin"));

        model.addAttribute("usuarioAdmin",
                (sesion.getAttribute("usuarioAdmin") != null) ? sesion.getAttribute("usuarioAdmin") : false);

        List<Usuario> usuarios = bdUsuario.findAll();
        model.addAttribute("usuario", usuarios);
        if (id != null) {
            Usuario usuarioEditar = bdUsuario.findById(id);
            if (usuarioEditar != null) {
                model.addAttribute("usuarioEditar", usuarioEditar);
                model.addAttribute("edit", true);
            } else {
                model.addAttribute("edit", false);
                model.addAttribute("error", "Usuario no encontrado");
            }
        }
        return "admin/zonaUsuarios";
    }

    @PostMapping("/zonaUsuarios/edit")
    public String actualizarUsuario(Usuario usuario) {
        Usuario usuarioExistente = bdUsuario.findById(usuario.getId());
        if (usuarioExistente != null) {
            usuario.setContraseña(usuarioExistente.getContraseña());
            if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
                usuario.setRol(usuarioExistente.getRol());
            }
            bdUsuario.edit(usuario);
        }
        return "redirect:/zonaUsuarios";
    }

    @GetMapping("/zonaCartas")
    public String zonaCartas(Model model) {

        model.addAttribute("currentUri", "/zonaCartas");
        model.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        model.addAttribute("usuarioAdmin", sesion.getAttribute("usuarioAdmin"));

        List<Categorias> categorias = bdCategoria.findAll();
        List<Carta> cartas = bdCartas.findAll();
        model.addAttribute("listadoCartas", cartas);
        model.addAttribute("categorias", categorias);
        return "admin/zonaCartas";
    }

    @GetMapping("/zonaCartas/getCarta")
    @ResponseBody
    public Carta getCarta(@RequestParam Long id) {
        Carta carta = bdCartas.findById(id);
        return carta;
    }

    @PostMapping("/zonaCartas/edit")
    public String actualizarCarta(@RequestParam("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("coste") String coste,
            @RequestParam("color") String color,
            @RequestParam("codigo") String codigo,
            @RequestParam("precio") Integer precio,
            @RequestParam("cantidad") Integer cantidad) {
        Carta carta = bdCartas.findById(id);
        if (carta == null) {
            return "redirect:/zonaCartas?error=cartaNotFound";
        }

        carta.setNombreCarta(nombre);
        carta.setTipoCarta(tipo);
        carta.setCosteCarta(coste);
        carta.setColor(color);
        carta.setCodigoCarta(codigo);
        carta.setPrecioCarta(precio);
        carta.setCantidad(cantidad);

        // Guardar la carta en la base de datos
        bdCartas.edit(carta);

        return "redirect:/zonaCartas";
    }

    @PostMapping("/zonaCartas/add")
    public String añadirCarta(@RequestParam("nombre") String nombre,
            @RequestParam("tipo") String tipo,
            @RequestParam("coste") String coste,
            @RequestParam("color") String color,
            @RequestParam("codigo") String codigo,
            @RequestParam("precio") Integer precio,
            @RequestParam("cantidad") Integer cantidad,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("categorias") String categoriasIds) {
        Carta nuevaCarta = new Carta();
        nuevaCarta.setNombreCarta(nombre);
        nuevaCarta.setTipoCarta(tipo);
        nuevaCarta.setCosteCarta(coste);
        nuevaCarta.setColor(color);
        nuevaCarta.setCodigoCarta(codigo);
        nuevaCarta.setPrecioCarta(precio);
        nuevaCarta.setCantidad(cantidad);

        // Guardar la imagen
        if (!imagen.isEmpty()) {
            try {
                String nombreImagen = imagen.getOriginalFilename();
                String rutaImagen = new File("src/main/resources/static/img/" + nombreImagen).getAbsolutePath();
                File archivoImagen = new File(rutaImagen);
                imagen.transferTo(archivoImagen);
                nuevaCarta.setImg(nombreImagen);
                System.out.println("Imagen guardada en: " + rutaImagen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Long> Listacategorias = Arrays.stream(categoriasIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        for (Long categoriaId : Listacategorias) {
            Categorias categoria = bdCategoria.findById(categoriaId);
            if (categoria != null) {
                categoria.addCarta(nuevaCarta);
            }
        }

        // Guardar la carta en la base de datos
        bdCartas.add(nuevaCarta);

        return "redirect:/zonaCartas";
    }
}

